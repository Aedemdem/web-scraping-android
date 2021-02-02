public class FragmentBerita extends Fragment {
    WebView webView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        webView = view.findViewById(R.id.webviewInformasi);
        new MyTask().execute();
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTask extends AsyncTask<Void, Void, Void> {
        String result;
        Elements elements;
        @Override
        protected Void doInBackground(Void... voids) {
            String urli = "https://yourdomain.com";
            try {
                Document doc = Jsoup.connect(urli).get();
                Elements ele = doc.select("div.row");
                elements = ele;
                
                Log.d("SCRAP", ele.toString());
                result = "ok";
            } catch (IOException e) {
                e.printStackTrace();
                result = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (result.matches("ok")) {
                webView.loadData(elements.toString(),"text/html","utf-8");
            }
//            textMessage.setText(result);
//            textLoad.setText("Finished");
            super.onPostExecute(aVoid);
        }
    }
}
