package com.example.app_taex_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreguntasFActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_f);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Preguntas Frecuentes");
        setSupportActionBar(toolbar);

        ExpandableListView expandableListView = findViewById(R.id.expandableListView);

        HashMap<String, List<String>> item = new HashMap<>();

        ArrayList<String> Pregunta1 = new ArrayList<>();
        Pregunta1.add("OBUN se encuentra ubicada en la ex oficina de la Dirección de Escuela de Ingeniería de Sistemas.");
        item.put("¿Dónde está ubicada la Oficina de Bienestar Universitario (OBUN)?",Pregunta1);

        ArrayList<String> Pregunta2 = new ArrayList<>();
        Pregunta2.add("Las inscripciones a talleres extracurriculares empiezan en la semana de matrícula del semestre académico vigente.");
        item.put("¿Cuándo empiezan las inscripciones?",Pregunta2);

        ArrayList<String> Pregunta3 = new ArrayList<>();
        Pregunta3.add("Cuando ingresas a la aplicación TAEX, debes seleccionar la categoría de talleres, luego puedes buscar el taller que deseas y hacer clic sobre el mismo para ver los detalles en el detalle del taller encontrarás el botón de inscripción, sólo debes completar un par de datos, confirmar tu inscripción y ¡Listo!");
        item.put("¿Cómo me puedo inscribir a un taller?",Pregunta3);

        ArrayList<String> Pregunta4 = new ArrayList<>();
        Pregunta4.add("Dentro de la aplicación TAEX, tienes la opción Mis Talleres, donde podrás ver la nota final obtenida en el taller");
        item.put("¿Dónde puedo ver mi nota final del taller?",Pregunta4);

        ArrayList<String> Pregunta5 = new ArrayList<>();
        Pregunta5.add("Los alumnos que tengan código a partir del 2016, deben llevar de forma obligatoria 2 talleres extracurriculares para poder egresar de la Universidad.\n");
        item.put("¿Quienes deben llevar los talleres extracurriculares?",Pregunta5);

        ArrayList<String> Pregunta6 = new ArrayList<>();
        Pregunta6.add("Puedes verificar los cupos diponibles en el detalle de cada taller extracurricular, debajo de la información del docente.");
        item.put("¿Cómo puedo saber cuántos cupos disponibles hay en cada taller?",Pregunta6);

        ArrayList<String> Pregunta7 = new ArrayList<>();
        Pregunta7.add("Los alumnos con código 2016 y 2017, deben pagar S/ 125.00 por cada taller.\n" +
                "\n" +
                "Los alumnos con código 2018 y 2019, no deben pagar adicional. El costo de los TAEX está incluido en la pensión");
        item.put("¿Cuál es el costo de un taller extracurricular?",Pregunta7);

        ArrayList<String> Pregunta8 = new ArrayList<>();
        Pregunta8.add("Para retirarte de un taller deberás presentar un FUT a la oficina de OBUN, especificando el motivo. \n " +
                "El FUT deberá ser presentado en la semana  posterior a la inscripción.");
        item.put("¿Cómo me retiro de un taller extracurricular?",Pregunta8);



        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setListViewHeight(parent,groupPosition);
                return false;
            }
        });

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(item);
        expandableListView.setAdapter(adapter);
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        MyExpandableListAdapter listAdapter = (MyExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}
