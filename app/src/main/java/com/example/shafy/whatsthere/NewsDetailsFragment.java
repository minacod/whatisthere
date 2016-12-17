package com.example.shafy.whatsthere;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafy.whatsthere.Data.NewsDbHelper;
import com.example.shafy.whatsthere.Data.WhatsThereContract;
import com.example.shafy.whatsthere.Data.WhatsThereContract.NewsTable;
import com.example.shafy.whatsthere.Utils.News;
import com.squareup.picasso.Picasso;

/**
 * Created by mina essam on 14-Dec-16.
 */
public class NewsDetailsFragment extends Fragment {
    ImageView image;
    TextView author;
    TextView title;
    TextView description;
    TextView date;
    Button more;
    ImageView star;
    boolean starState;
    int position;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View fragment=inflater.inflate(R.layout.details_fragment,container,false);
        image=(ImageView)fragment.findViewById(R.id.details_image);
        author=(TextView) fragment.findViewById(R.id.details_autor);
        title=(TextView) fragment.findViewById(R.id.details_title);
        description=(TextView) fragment.findViewById(R.id.details_description);
        date=(TextView) fragment.findViewById(R.id.details_date);
        more=(Button) fragment.findViewById(R.id.continue_reading_button);
        star=(ImageView) fragment.findViewById(R.id.details_star);
        Bundle bundle=getArguments();
         position=bundle.getInt("pos");
        Log.v("details fragment",String.valueOf(position));
        Log.v("details fragment",News.news.get(position).getNewsImageURL());
        Picasso.with(getContext()).load(News.news.get(position).getNewsImageURL()).error(R.drawable.no).into(image);
        String name=News.news.get(position).getAuthor();
        if(!name.equals("null"))
        {
            author.setText(name);
                    }
        else {
            author.setText("Unknown");
        }
        title.setText(News.news.get(position).getTitle());
        description.setText(News.news.get(position).getDescription());
        date.setText(News.news.get(position).getDate());

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(News.news.get(position).getNewsURL()));
                startActivity(intent);
            }
        });
        if(isInDatabase()){
            starState=true;
            star.setImageResource(R.drawable.ic_home_black_48px);
        }
        else {
            starState=false;
            String s="f";
            Log.v("ss", s );
            star.setImageResource(R.drawable.ic_favorite_black_48px);
        }

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is a favourite and i should remove it
                if(starState){
                    starState=false;
                    star.setImageResource(R.drawable.ic_favorite_black_48px);
                    removeFavouriteNews();
                    Toast.makeText(getContext(),"Deleted From Favourites",Toast.LENGTH_LONG).show();
                }
                //this is not a favourite and i should add it
                else {
                    starState=true;
                    star.setImageResource(R.drawable.ic_home_black_48px);
                    addFavouriteNews();
                    Toast.makeText(getContext(),"Added To Favourites",Toast.LENGTH_LONG).show();
                }
            }
        });
        return fragment;
    }

    void addFavouriteNews(){

        NewsDbHelper helper=new NewsDbHelper(getContext());
        SQLiteDatabase database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NewsTable.AUTHOR_COLUMN,News.news.get(position).getAuthor());
        values.put(NewsTable.TITLE_COLUMN,News.news.get(position).getTitle());
        values.put(NewsTable.DESCRIPTION_COLUMN,News.news.get(position).getDescription());
        values.put(NewsTable.DATE_COLUMN,News.news.get(position).getDate());
        values.put(NewsTable.IMAGE_URL,News.news.get(position).getNewsImageURL());
        values.put(NewsTable.NEWS_URL,News.news.get(position).getNewsURL());
        database.insert(NewsTable.TABLE_NAME,null,values);
        database.close();
    }

    void removeFavouriteNews(){
        NewsDbHelper helper=new NewsDbHelper(getContext());
        SQLiteDatabase database=helper.getWritableDatabase();
        String[] args=new String[1];
        args[0]=News.news.get(position).getNewsImageURL();
        database.delete(NewsTable.TABLE_NAME,NewsTable.IMAGE_URL+"=?",args);
        database.close();

    }

     boolean isInDatabase(){
        NewsDbHelper helper=new NewsDbHelper(getContext());
        SQLiteDatabase database=helper.getWritableDatabase();
        String[] selection={News.news.get(position).getNewsImageURL()};
        Cursor cursor=database.rawQuery(WhatsThereContract.SELECT_ROW_BY_IMAGE_URL +" =? ",selection);

        if(cursor.getCount()>0) {
            database.close();
            return true;
        }
        else {
            database.close();
            return false;
        }
    }
}
