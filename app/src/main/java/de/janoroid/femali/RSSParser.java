package de.janoroid.femali;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RSSParser extends AsyncTask<Void,Void,Object> {

    String title = null;
    String link = null;
    String description = null;
    String image = null;
    String keywords = null;
    String duration = null;
    String date = null;
    String audioUrl = null;
    String category = null;
    String summary = null;
    boolean isItemAvailable = false;
    List<RSSItems> items = new ArrayList<>();
    InputStream inputStream;


    @Override
    protected Object doInBackground(Void... rssURLs) {

        try {

            URL rssUrl = new URL("https://rotundlang.podigee.io/feed/mp3");

            inputStream = rssUrl.openStream();

            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItemAvailable = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItemAvailable = true;
                        continue;
                    }
                }

                //The String get the geparst Value
                String result = "";

                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {

                    Log.d("XmlParser", "Parsing title ==> " + result);
                    title = result;

                } else if (name.equalsIgnoreCase("link")) {

                    Log.d("XmlParser", "Parsing link ==> " + result);
                    link = result;

                } else if (name.equalsIgnoreCase("description")) {

                    Log.d("XmlParser", "Parsing description ==> " + result);
                    description = result;

                } else if (name.equalsIgnoreCase("itunes:duration")) {

                    Log.d("XmlParser", "Parsing duration ==> " + result);
                    duration = result;

                } else if (name.equalsIgnoreCase("itunes:image")) {

                    Log.d("XmlParser", "Parsing image ==> " + image);
                    image = result;

                } else if (name.equalsIgnoreCase("itunes:keywords")) {

                    Log.d("XmlParser", "Parsing keywords ==> " + result);
                    keywords = result;

                } else if (name.equalsIgnoreCase("pubDate")) {

                    Log.d("XmlParser", "Parsing pubDate ==> " + result);
                    date = result;

                } else if (name.equalsIgnoreCase("enclosure")) {

                    Log.d("XmlParser", "Parsing audio ==> " + result);
                    audioUrl = result;

                } else if (name.equalsIgnoreCase("category")) {

                    Log.d("XmlParser", "Parsing category ==> " + result);
                    category = result;

                } else if (name.equalsIgnoreCase("itunes:summary")) {

                    Log.d("XmlParser", "Parsing summary ==> " + result);
                    summary = result;
                }

                if (title != null && link != null && description != null) {
                    if (isItemAvailable) {
                        RSSItems item = new RSSItems(title, description, image, date, duration, audioUrl, keywords, category, summary);
                        items.add(item);
                    }

                    title = null;
                    link = null;
                    description = null;
                    image = null;
                    keywords = null;
                    duration = null;
                    date = null;
                    audioUrl = null;
                    category = null;
                    summary = null;
                    isItemAvailable = false;

                }
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return isItemAvailable;

    }

}

