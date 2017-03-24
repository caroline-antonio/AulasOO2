package projetoscarol.tabwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec aba1 = tabHost.newTabSpec("primeira");
        aba1.setContent(R.id.primeiraAba);
        aba1.setIndicator("primeira");

        TabHost.TabSpec aba2 = tabHost.newTabSpec("segunda");
        aba2.setContent(R.id.segundaAba);
        aba2.setIndicator("segunda");

        TabHost.TabSpec aba3 = tabHost.newTabSpec("terceira");
        aba3.setContent(R.id.terceiraAba);
        aba3.setIndicator("terceira");

        tabHost.addTab(aba1);
        tabHost.addTab(aba2);
        tabHost.addTab(aba3);


    }
}
