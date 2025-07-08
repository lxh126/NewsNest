public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            OkHttpClientManager.getInstance()
                    .setCertificates(getAssets().open("srca.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
}