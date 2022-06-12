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
import java.util.Arrays;
import java.util.List;


public class RSSParser extends AsyncTask<ArrayList<Object>, Void, Object> {

    private String title = null;
    private String links = null;
    private String description = null;
    private String image = null;
    private String keywords = null;
    private String duration = null;
    private String date = null;
    private String audioUrl = null;
    private String category = null;
    private String summary = null;
    private boolean isItemAvailable = false;
    InputStream inputStream;


    @Override
    protected Object doInBackground(ArrayList<Object>... linksArrayList) {

        try {

            for (ArrayList<Object> arrayList : linksArrayList) {

                Log.d("once call", arrayList.toString());

                //get the Links of the Arraylist
                URL rssUrl = new URL((String) arrayList.get(0));

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
                        links = result;

                    } else if (name.equalsIgnoreCase("description")) {

                        Log.d("XmlParser", "Parsing description ==> " + result);
                        description = result;

                    } else if (name.equalsIgnoreCase("itunes:duration")) {

                        Log.d("XmlParser", "Parsing duration ==> " + result);
                        duration = result;

                    } else if (name.equalsIgnoreCase("itunes:image")) {

                        int attributeCount = xmlPullParser.getAttributeCount();

                        for (int i = 0; i < attributeCount; i++) {

                            String attrib = xmlPullParser.getAttributeName(i);

                            if (attrib.equalsIgnoreCase("href")) {

                                result = xmlPullParser.getAttributeValue(i);

                                Log.d("XmlParser", "Parsing imageUrl ==> " + result);
                                image = result;
                            }
                        }

                    } else if (name.equalsIgnoreCase("itunes:keywords")) {


                        Log.d("XmlParser", "Parsing keywords ==> " + result);
                        keywords = result;

                    } else if (name.equalsIgnoreCase("pubDate")) {

                        Log.d("XmlParser", "Parsing pubDate ==> " + result);
                        date = result;

                    } else if (name.equalsIgnoreCase("enclosure")) {

                        int attributeCount = xmlPullParser.getAttributeCount();

                        for (int i = 0; i < attributeCount; i++) {
                            String attrib = xmlPullParser.getAttributeName(i);

                            if (attrib.equalsIgnoreCase("url")) {
                                result = xmlPullParser.getAttributeValue(i);

                                Log.d("XmlParser", "Parsing audioURL ==> " + result);
                                audioUrl = result;
                            }
                        }

                    } else if (name.equalsIgnoreCase("category")) {

                        Log.d("XmlParser", "Parsing category ==> " + result);
                        category = result;

                    } else if (name.equalsIgnoreCase("itunes:summary")) {

                        Log.d("XmlParser", "Parsing summary ==> " + result);
                        summary = result;
                    }

                    //get the Information of RSS-Feed
                    if (isItemAvailable) {
                        RSSItems rssItems = new RSSItems(title, description, image, date, duration, audioUrl, keywords, category, summary);
                        List<RSSItems> items = new ArrayList<>();
                        items.add(rssItems);
                    }
                }

                title = null;
                links = null;
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

            } catch(XmlPullParserException | IOException e){
                e.printStackTrace();
            }


            return isItemAvailable;

        }

    }

