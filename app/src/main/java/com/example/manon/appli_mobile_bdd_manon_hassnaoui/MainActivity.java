package com.example.manon.appli_mobile_bdd_manon_hassnaoui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ListActivity {
    private final int RESULT_SELECTION=1;
    private ChapterDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new ChapterDataSource(this);
        dataSource.open();
        List<Chapter> values = dataSource.getAllChapter();

        //on utilise le SimpleCursorAdapter pour montrer les éléments dans une listView.
        ArrayAdapter<Chapter> adapter = new ArrayAdapter<Chapter>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }



    //switch case, bouton add va sur la seconde activité, le bouton delete supprime la
    //première ligne
    public void onClick(View view)
    {
        ArrayAdapter<Chapter> adapter = (ArrayAdapter<Chapter>) getListAdapter();
        Chapter chapter = null;

        switch (view.getId()){
            case R.id.add:
                goToNextActivity();
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0)
                {
                    chapter = (Chapter) getListAdapter().getItem(0);
                    dataSource.deleteChapter(chapter);
                    adapter.remove(chapter);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    //va à la prochaine activité
    private void goToNextActivity(){
        Intent intent = new Intent(MainActivity.this, creation.class);
        startActivityForResult(intent,RESULT_SELECTION);
    }


    @Override
    protected void onResume(){
        dataSource.open();
        super.onResume();
    }


    @Override
    protected void onPause(){
        dataSource.close();
        super.onPause();
    }

    //retour dans l'activité principale avec les resultat
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        dataSource.open();
        ArrayAdapter<Chapter> adapter = (ArrayAdapter<Chapter>) getListAdapter();
        Chapter chapter = null;
        String name  = intent.getStringExtra("name");
        String description = intent.getStringExtra("desc");

        chapter = dataSource.createChapter(name, description);
        adapter.add(chapter);
        dataSource.close();


    }


}
