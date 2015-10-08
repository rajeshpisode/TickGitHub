package com.piyush.tick.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager.BadTokenException;
import android.widget.ImageView;
import com.piyush.tick.R;
import com.piyush.tick.utils.TicTackToeConstants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Common
{

	private static String SDCARD = Environment.getExternalStorageDirectory()
			.getPath() + "/";

	public static void downLoadImage(String path, String filename) {
		int count;
		try {

			URL url = new URL(path);
			URLConnection conection = url.openConnection();
			conection.connect();
			// getting file length

			// input stream to read file - with 8k buffer
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
			File file = new File(SDCARD + "Reacho/");
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				}

			}
			// Output stream to write file
			OutputStream output = new FileOutputStream(SDCARD + "Reacho/"
					+ filename);

			byte data[] = new byte[1024];

			long total = 0;

			while ((count = input.read(data)) != -1) {
				total += count;
				// publishing the progress....
				// After this onProgressUpdate will be called
				// publishProgress(""+(int)((total*100)/lenghtOfFile));

				// writing data to file
				output.write(data, 0, count);
			}

			output.flush();

			// closing streams
			output.close();
			input.close();

		} catch (Exception e) {
			// Log.e("Error: ", e.getMessage());
		}
	}

	public static boolean getDrawable(Activity activity, String filename,
			int which, ImageView img, int size) {
		try {
			String sdDir = SDCARD + "Reacho/";

			BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			DisplayMetrics metrics = activity.getResources()
					.getDisplayMetrics();

			Bitmap tmpBit = BitmapFactory.decodeFile(sdDir + filename, options);
			Drawable tmpDraw = (Drawable) new BitmapDrawable(
					activity.getResources(), /* tmpBit */getRoundedShape(tmpBit,
							size));
			TicTackToeConstants.USER_IMAGE = tmpDraw;
			img.setImageDrawable(TicTackToeConstants.USER_IMAGE);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// SharedPreferences prefs = activity.getSharedPreferences(
			// Constant.KEY_SHARED_PREFRANCE, Context.MODE_PRIVATE);
			// Common.downLoadImage(
			// prefs.getString(Constant.KEY_USER_IMAGE_PATH, "")
			// + Constant.KEY_IMAGE_SIZE_100 + "/"
			// + prefs.getString(Constant.KEY_USER_PHOTO, ""),
			// Constant.KEY_USER_IMAGE);
			// e.printStackTrace();
			return false;
		}
	}

	public static Bitmap getRoundedShape(Bitmap scaleBitmapImage, int size) {
		int targetWidth = size;
		int targetHeight = size;
		Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(targetBitmap);
		Path path = new Path();
		path.addCircle(((float) targetWidth - 1) / 2,
				((float) targetHeight - 1) / 2,
				(Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
				Path.Direction.CCW);

		canvas.clipPath(path);
		Bitmap sourceBitmap = scaleBitmapImage;
		canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight()), new Rect(0, 0, targetWidth,
				targetHeight), null);

		return targetBitmap;
	}

	public static Bitmap getRoundedShape1(Bitmap scaleBitmapImage, int size) {
		int targetWidth = size;
		int targetHeight = size;
		Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(targetBitmap);
		Path path = new Path();
		path.addCircle(((float) targetWidth) / 2, ((float) targetHeight) / 2,
				(Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
				Path.Direction.CW);
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setFilterBitmap(true);
		canvas.drawOval(new RectF(0, 0, targetWidth, targetHeight), paint);
		paint.setColor(Color.TRANSPARENT);
		canvas.clipPath(path);
		Bitmap sourceBitmap = scaleBitmapImage;
		canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight()), new RectF(0, 0, targetWidth,
				targetHeight), null);
		return targetBitmap;
	}

	/**
	 * Method for hiding keyborad
	 * 
	 * @param
	 *
     * @author rajesh
	 */
	// public static void hideKeyboard(EditText myEditText) {
	// InputMethodManager imm = (InputMethodManager)
	// .getSystemService(Context.INPUT_METHOD_SERVICE);
	// imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
	// }

	public static ProgressDialog createProgressDialog(Context mContext) {
		ProgressDialog dialog = new ProgressDialog(mContext);
		try {
			dialog.show();
		} catch (BadTokenException e) {

		}
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.progerss_dialog);
		// dialog.setMessage(Message);
		return dialog;
	}

	static boolean flag;

	public static boolean confirmationDialog(Activity activity, String title,
			String msg) {
		flag = false;
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setIcon(R.drawable.ic_launcher);
		alert.setTitle(title);
		alert.setMessage(msg);
		alert.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// System.exit(0);
						flag = true;
					}
				});
		alert.setNegativeButton(android.R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						flag = false;
					}
				});
		final AlertDialog dialog = alert.create();
		dialog.show();
		return flag;
	}

	public static boolean alertDialog(Activity activity, String title,
			String msg) {
		flag = false;
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setIcon(R.drawable.ic_launcher);
		alert.setTitle(title);
		alert.setMessage(msg);
		alert.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// System.exit(0);
						flag = true;

					}
				});

		final AlertDialog dialog = alert.create();
		dialog.show();
		return flag;
	}
    public static boolean isFileExists(String folderName, String filename){
        File folder = new File(Environment.getExternalStorageDirectory().toString()+folderName);
        File[] listOfFiles = folder.listFiles();
        boolean isPresent = false;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().equalsIgnoreCase(filename)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    public static void alertDialogLogin(Activity activity, String title,
                                      String msg) {

        AlertDialog.Builder alert = new AlertDialog.Builder(activity, AlertDialog.THEME_HOLO_LIGHT);
        alert.setIcon(R.drawable.ic_launcher);
        alert.setCancelable(false);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // System.exit(0);
                        dialog.dismiss();
                    }
                });

        final AlertDialog dialog = alert.create();
        dialog.show();
    }
	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size=1024;
		try
		{

			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				//Read byte from input stream

				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;

				//Write byte from output stream
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){}
	}

}
