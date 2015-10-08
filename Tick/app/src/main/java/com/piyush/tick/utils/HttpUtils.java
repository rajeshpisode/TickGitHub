package com.piyush.tick.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.piyush.tick.exception.TicTackToeException;

@SuppressWarnings("deprecation")
public class HttpUtils {
	private static final int TIME_OUT = 10 * 1000;
	public static final String SSO_HEADER_SMALL = "sso-token";
	public static final String SSO_HEADER_CAMLE_CASING = "ssoToken";

	private static final String TAG = HttpUtils.class.getSimpleName();

	/**
	 * SIT API Key
	 */
	public static String API_KEY = "l7xxc9bf8f51c2d84474ab76e9614e6b89c6";
	private Context mContext;

	public HttpUtils(Context context) {
		this.mContext = context;
	}


	

	public String doGetRaw(String stringURL, String SSOHeader) throws TicTackToeException {
		String respoString = null;
		try {
			URL url = new URL(stringURL);
			MyHostnameVerifier verifier = new MyHostnameVerifier();
			HttpsURLConnection
					.setDefaultHostnameVerifier(verifier);
			HttpsURLConnection.setDefaultHostnameVerifier(verifier);
			try {
				trustAllHttpsCertificates();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setRequestProperty("X-API-Key", API_KEY);
			if (SSOHeader != null)
				httpConn.setRequestProperty(SSOHeader, TicTackToeSharedPrefrence.getInstance(mContext)
						.fetchStringPrefernce(TicTackToeConstants.PREF_KEY_SSOTOKEN,""));
			httpConn.setRequestMethod("GET");
			httpConn.setReadTimeout(TIME_OUT);
			httpConn.setConnectTimeout(TIME_OUT);

			InputStreamReader isr = new InputStreamReader(
					httpConn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String line;
			String tempResponse = "";

			// Create a string using response from web services
			while ((line = br.readLine()) != null)
				tempResponse = tempResponse + line;
			respoString = tempResponse;
			if (TextUtils.isEmpty(respoString))
				throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);
			try {
				ErrorResponse error = new Gson().fromJson(respoString,ErrorResponse.class);
				if (error.getSuccess() == false)
					throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.d(TAG, "JSON-->" + respoString);

		} catch (IOException e) {
			throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_TIMEOUT,TicTackToeConstants.ERROR_MSG_TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);

		}
		return respoString;
	}

	public String doPostRaw(String object, String URL, String SSOHeader,
			boolean isXMLRequest)  throws TicTackToeException{
		String result = null;
		try {

			try {
				SSLContext sslContext = SSLContext.getInstance("TLS");
				TrustManager[] trustMgr = getTrustMgr();
				sslContext.init(null, // key manager
						trustMgr, // trust manager
						new SecureRandom()); // random number generator
				HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
						.getSocketFactory());
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = getNewHttpClient();

				HttpPost httpPost = new HttpPost(URL);
				httpPost.getParams().setBooleanParameter(
						CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
				if (isXMLRequest)
					httpPost.setHeader("Content-Type", "application/xml");
				else
					httpPost.setHeader("Content-Type", "application/json");
				if (SSOHeader != null)
					httpPost.setHeader(SSOHeader, TicTackToeSharedPrefrence.getInstance(mContext)
							.fetchStringPrefernce(
									TicTackToeConstants.PREF_KEY_SSOTOKEN, ""));
				httpPost.setHeader("X-API-Key", API_KEY);
				StringEntity entity = new StringEntity(object, HTTP.UTF_8);
				httpPost.setEntity(entity);
				HttpResponse httpResponse = httpClient.execute(httpPost);

				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK || statusCode == 204) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity == null)
						result = "";
					else
						result = EntityUtils.toString(httpEntity);
				} else {
					String reasonPhase = httpResponse.getStatusLine().getReasonPhrase();
					throw new TicTackToeException(statusCode + "", reasonPhase);

				}

			} catch (IOException e) {
				throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_TIMEOUT,TicTackToeConstants.ERROR_MSG_TIMEOUT);
			} catch (Exception e) {
				e.printStackTrace();
				throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);

			}
		} catch (Exception e) {
			Log.e("" + System.class, e.getMessage());
			throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);
		}
		return result;
	}

	public HttpClient getNewHttpClient() {

		try {

			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());

			trustStore.load(null, null);

			SSLSocketFactory sf = new MSCSSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();

			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			// modified by ashish sharma
			// added new timeout
			HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
			HttpConnectionParams.setSoTimeout(params, TIME_OUT);

			SchemeRegistry registry = new SchemeRegistry();

			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));

			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);

		} catch (Exception e) {
			return null;
		}

	}

	private TrustManager[] getTrustMgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String t)
					throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] certs, String t)
					throws CertificateException {
			}
		} };
		return certs;
	}

	class MyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String urlHostName, String certHostName) {
			return true;
		}

		public boolean verify(String urlHost, SSLSession sslSession) {
			return true;
		}
	}

	public class miTM implements TrustManager,
			X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}

		public void checkClientTrusted(
				X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}
	}

	private void trustAllHttpsCertificates() throws Exception {

		// Create a trust manager that does not validate certificate chains:
		TrustManager[] trustAllCerts =

		new TrustManager[1];

		TrustManager tm = new miTM();

		trustAllCerts[0] = tm;

		SSLContext sc =

		SSLContext.getInstance("SSL");

		sc.init(null, trustAllCerts, null);

		HttpsURLConnection.setDefaultSSLSocketFactory(

		sc.getSocketFactory());

	}



	public String doPut(String object, String URL)  throws TicTackToeException{
		String result = null;
		try {

			try {
				SSLContext sslContext = SSLContext.getInstance("TLS");
				TrustManager[] trustMgr = getTrustMgr();
				sslContext.init(null, // key manager
						trustMgr, // trust manager
						new SecureRandom()); // random number generator
				HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
						.getSocketFactory());
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = getNewHttpClient();
				HttpPut httpPut = new HttpPut(URL);
				httpPut.getParams().setBooleanParameter(
						CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
				httpPut.setHeader("Content-Type", "application/json");

				httpPut.setHeader("Accept", "application/json");
				httpPut.setHeader("X-API-Key", API_KEY);
				StringEntity entity = new StringEntity(object, HTTP.UTF_8);
				httpPut.setEntity(entity);
				HttpResponse httpResponse = httpClient.execute(httpPut);
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK || statusCode == 204) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity == null)
						result = "";
					else
						result = EntityUtils.toString(httpEntity);
				} else {
					throw new TicTackToeException(httpResponse.getStatusLine().getStatusCode()+"",httpResponse.getStatusLine().getReasonPhrase());
				}

			} catch (IOException e) {
				throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_TIMEOUT,TicTackToeConstants.ERROR_MSG_TIMEOUT);
			} catch (Exception e) {
				e.printStackTrace();
				throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);
			}
		} catch (Exception e) {
			Log.e("" + System.class, e.getMessage());
			throw new TicTackToeException(TicTackToeConstants.ERROR_CODE_UNKNOWN,TicTackToeConstants.ERROR_MSG_UNKNOWN);
		}
		return result;
	}

	public void doDelete() {
	}

}
