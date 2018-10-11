package com.fredrick.hci.prinyme;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DisplayPicturesActivity extends AppCompatActivity {

    Map<String, String> imageSource;
    StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pictures);

        final TextView htmlRes = (TextView)findViewById(R.id.textView);
        Ion.with(getApplicationContext())
                .load("https://anime.goodfon.ru/index-2.html")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        htmlParse(result);
                    }
                });
    }


    private void htmlParse(String html)
    {
        imageSource = new HashMap<String, String>();

        try
        {
            String[] lines = html.split(System.getProperty("line.separator"));

            // href and image starts from 333 line
            // and their between distance are 33 lines
            for (int i = 333; i < 1105; i+=33)
            {
                // Skip google addition in html and continue iteration
                if (i == 498)
                {
                    i = 513;
                    continue;
                }
                else if (i == 711)
                {
                    i = 726;
                    continue;
                }
                else if (i == 924)
                {
                    i = 939;
                    continue;
                }

                imageSource.put(lineParseHref(lines[i]), lineParseSrc(lines[i+1]));
            }


        }
        catch (Exception exception)
        {
            System.out.println("htmlParse Error: " + exception.getMessage());
        }
    }

    @Nullable
    private String lineParseHref(String line)
    {
        try
        {
            builder = new StringBuilder();
            for (int i = 0; i < line.length(); i++)
            {
                char currentChar = line.charAt(i);

                if (currentChar != ' ')
                {
                    builder.append(currentChar);
                }
                else if (builder.length() != 0)
                {
                    // If string in builder is href
                    if (builder.charAt(0) == 'h' && builder.charAt(1) == 'r')
                    {
                        // Remove 'href"' and last "
                        builder.delete(0, 6);
                        builder.deleteCharAt(builder.length() - 1);
                        return builder.toString();
                    }

                    // Clear the StringBuilder
                    builder.setLength(0);
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println("lineParseHref Error: " + exception.getMessage());
        }

        return null;
    }


    @Nullable
    private String lineParseSrc(String line)
    {
        try
        {
            builder = new StringBuilder();
            for (int i = 0; i < line.length(); i++)
            {
                char currentChar = line.charAt(i);

                if (currentChar != ' ')
                {
                    builder.append(currentChar);
                }
                else if (builder.length() != 0)
                {
                    // If string in builder is src
                    if (builder.charAt(0) == 's')
                    {
                        // Remove 'src=' and last "
                        builder.delete(0, 5);
                        builder.deleteCharAt(builder.length() - 1);
                        return builder.toString();
                    }

                    // Clear the StringBuilder
                    builder.setLength(0);
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println("lineParseSrc Error: " + exception.getMessage());
        }

        return null;
    }


    // FIX THIS!!
    @Nullable
    private String[] removeElements(String[] array, int from, int count)
    {
        try
        {
            ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(array));

            for (int i = from; i < from+count; i++)
            {
                tmp.remove(i);
            }

            return tmp.toArray(new String[tmp.size()]);
        }
        catch (Exception exception)
        {
            System.out.println("removeElements Error: " + exception.getMessage());
        }

        return null;
    }
}
