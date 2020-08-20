package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;


public class MenuPrincipalActivity extends AppCompatActivity implements View.OnClickListener, AIListener {

    //dialogflow
    FloatingActionButton multiple_actions;
    FloatingActionButton mic;
    FloatingActionButton mic2;
    FloatingActionButton share;
    FloatingActionButton phone;
    FloatingActionButton whatsapp;
    FloatingActionButton video;
    FloatingActionButton chat;
    private TextToSpeech mTextToSpeech;
    private AIService mAIService;
    ActionBar actionBar;

    boolean isMenuOpen = false;
    //


    //PARA VIEWPAGER DE COMUNICADOS ***************************
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotsLayout;

    //Para almacenar la información que se guardará en cada fragment
    private int[] image = {R.drawable.publicidadtaex02, R.drawable.publicidadtaex01, R.drawable.publicidadtaex03};
    private int[] colorBackground, colorDot;
    private TextView[] dots;

    // **********************************************************

    private CardView talleresCardView, notasCardView, comentariosCardView, preguntasCardView,perfilCardView,TallerperfilCardView;
    private CardView admicardview, docentecardview, alumnocardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        talleresCardView = findViewById(R.id.danzaCV);
        notasCardView = findViewById(R.id.deporteCV);
        comentariosCardView = findViewById(R.id.personalesCV);

        /*admicardview = findViewById(R.id.ADMICV);
        docentecardview = findViewById(R.id.DOCENTECV);
        alumnocardview = findViewById(R.id.ESTUDIANTECV);*/

        talleresCardView.setOnClickListener(this);
        notasCardView.setOnClickListener(this);
        comentariosCardView.setOnClickListener(this);

        /*admicardview.setOnClickListener(this);
        docentecardview.setOnClickListener(this);
        alumnocardview.setOnClickListener(this);*/

        // PARA SLIDE ********************************
        colorBackground = getResources().getIntArray(R.array.array_background);
        colorDot = getResources().getIntArray(R.array.array_dot);
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);

        //*********************************************
        final AIConfiguration config = new AIConfiguration("0f96f5a7f23c49b6a9e16d6d0ce2b4dd",
                AIConfiguration.SupportedLanguages.Spanish,
                AIConfiguration.RecognitionEngine.System);
        mAIService = AIService.getService(this, config);
        mAIService.setListener(this);
        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        findViewById(R.id.micButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INICIA EL SERVICIO
                mAIService.startListening();
                //android.os.Process.killProcess(android.os.Process.myPid());
                //android.os.Process.killProcess(Process.THREAD_PRIORITY_URGENT_AUDIO);
            }
        });
        //
        findViewById(R.id.micButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INICIA EL SERVICIO
                mAIService.startListening();
                //android.os.Process.killProcess(android.os.Process.myPid());
                //android.os.Process.killProcess(Process.THREAD_PRIORITY_URGENT_AUDIO);
            }
        });
        //
        multiple_actions = findViewById(R.id.multiple_actions);
        mic = findViewById(R.id.micButton);
        mic2 = findViewById(R.id.micButton2);

        chat = findViewById(R.id.chatconversacion);

        multiple_actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOpen();
            }
        });


        //**********************************************************************

        //Métodos
        addDots(0);
        loadViewPager();
    }

    public void menuOpen(){
        if (!isMenuOpen){
            mic.animate().translationY(-getResources().getDimension(R.dimen.mic));
            mic2.animate().translationY(-getResources().getDimension(R.dimen.mic2));

            chat.animate().translationY(-getResources().getDimension(R.dimen.chat));

            isMenuOpen = true;
        }else{
            mic.animate().translationY(0);
            mic2.animate().translationY(0);

            chat.animate().translationY(0);

            isMenuOpen = false;
        }
    }


    private int getItem(int i)
    {

        return viewPager.getCurrentItem()+i;
    }


    //método recibe el fragment actual
    private void addDots(int currentPage)
    {
        dots = new TextView[image.length];

        dotsLayout.removeAllViews();

        for(int i=0;i<dots.length;i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            //Definir el color de los Puntos dependiendo de la posición
            if(i==currentPage)
            {
                dots[i].setTextColor(colorDot[currentPage]); //para el punto seleccionado
            }
            else
            {
                dots[i].setTextColor(Color.LTGRAY); //el punto estará de color gris
            }
            dotsLayout.addView(dots[i]);
        }
    }

    //Agregar los elementos al ViewPager
    private void loadViewPager()
    {
        adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //crear una instancia para cada fragment
        for(int i=0;i<image.length;i++)
        {
            adapter.addFragment(newInstance(image[i], colorBackground[i]));
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageListener);
    }

    //Para llenar los ViewPager con los fragmentos
    //Esta es la información que se envía
    private SliderFragment newInstance (int image, int color)
    {
        //Creando una instancia diferente del fragment para colocar otra información en su interior
        Bundle bundle = new Bundle();
        bundle.putInt("image", image);
        bundle.putInt("color", color);

        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //para detectar la página actual
        @Override
        public void onPageSelected(int position) {
            addDots(position);


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId())
        {
            case R.id.danzaCV:
            {
                intent = new Intent(this, ArtisticoActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.deporteCV:
            {
                intent = new Intent(this, DeporteActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.personalesCV:
            {
                intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
            }

            /*case R.id.preguntasCV:
            {
                intent = new Intent(this, PreguntasFActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.TalleresCV:
            {
                intent = new Intent(this, MisTalleresActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.perfilCV:
            {
                intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
                break;
            }*/
        }
    }

    @Override
    public void onResult(AIResponse response) {
        Result result = response.getResult();
        mTextToSpeech.speak(result.getFulfillment().getSpeech(), TextToSpeech.QUEUE_FLUSH, null, null);

    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    public void onclick_silenciar_boot(View view)
    {
        //DETIENE EL SERVICIO
        if(mTextToSpeech != null){
            mTextToSpeech.stop();
        }

    }


    public void abrirchat(View v){

        startActivity(new Intent(MenuPrincipalActivity.this , maps  .class));

    }

}
