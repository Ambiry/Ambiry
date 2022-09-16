package de.jukolai.ambiry;

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


public class Parser extends AsyncTask<ArrayList<Object>, Void, Object> {

    public static List<ParentModel> parentModelList = new ArrayList<>();
    private final List<ChildModel> childModelList = new ArrayList<>();
    private String title = null;
    private String links = null;
    private String description = null;
    private String image = null;
    private String keywords = null;
    private String duration = null;
    private String date = null;
    private String audioURL = null;
    private String category = null;
    private String summary = null;
    private String attribute = null;
    private String author = null;
    private boolean isItemAvailable = false;

    @Override
    protected final Object doInBackground(ArrayList<Object>... linksArrayList) {

        try {

            for (ArrayList<Object> arrayList : linksArrayList) {

                for (int j = 0; j < arrayList.size(); j++) {

                    //get the Links of the Arraylist
                    URL rssUrl = new URL(arrayList.get(j).toString());

                    InputStream inputStream = rssUrl.openStream();

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

                                attribute = xmlPullParser.getAttributeName(i);

                                if (attribute.equalsIgnoreCase("href")) {

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
                                attribute = xmlPullParser.getAttributeName(i);

                                if (attribute.equalsIgnoreCase("url")) {
                                    result = xmlPullParser.getAttributeValue(i);

                                    Log.d("XmlParser", "Parsing audioURL ==> " + result);
                                    audioURL = result;
                                }
                            }

                        } else if (name.equalsIgnoreCase("category") | name.equalsIgnoreCase("itunes:category")) {

                            Log.d("XmlParser", "Parsing category ==> " + result);
                            category = result;

                        } else if (name.equalsIgnoreCase("itunes:summary")) {

                            Log.d("XmlParser", "Parsing summary ==> " + result);
                            summary = result;
                        } else if (name.equalsIgnoreCase("itunes:author")) {

                            Log.d("XmlParser", "Parsing author ==> " + result);
                            author = result;
                        }

                        //get the Information of RSS-Feeds
                        if (isItemAvailable) {

                            childModelList.add(new ChildModel(title, description, image, date, duration, audioURL, keywords, summary, author));

                        }

                    }
                }
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }


        return isItemAvailable;


    }


}


