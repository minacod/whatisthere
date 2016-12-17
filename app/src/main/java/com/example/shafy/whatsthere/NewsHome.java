package com.example.shafy.whatsthere;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NewsHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean mIsTwoPne=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sidebar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NewsFeedFragment newsFragment=new NewsFeedFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,newsFragment).commit();


        if(findViewById(R.id.news_details_fragment)!=null){
            mIsTwoPne=true;
        }
        else {
            mIsTwoPne=false;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.home){
            NewsFeedFragment newsFeedFragment=new NewsFeedFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,newsFeedFragment).commit();
        }
        if (id==R.id.favorite){

            Intent intent=new Intent(this,Favourite.class);
            intent.putExtra("fragment","News");
            startActivity(intent);

        }
        if(id==R.id.general){

            CategoryFragment CategoryFragment=new CategoryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.business){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("business");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.music){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("music");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.technology){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("technology");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.entertainment){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("entertainment");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.sport){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("sport");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.science_and_nature){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("science-and-nature");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }
        if(id==R.id.gaming){

            CategoryFragment CategoryFragment=new CategoryFragment();
            CategoryFragment.setCat("gaming");
            getSupportFragmentManager().beginTransaction().replace(R.id.news_first_look,CategoryFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
