package com.example.app_taex_1;
import java.util.Comparator;


public class Taller {
    String id_taller;
    String nombre_taller;
    String descripcion_taller;
    String dia_taller;
    String hora_taller;
    String lugar;
    String genero;
    String photo_taller;
    String id_docente;
    String cant_Inscritos;
    String cant_Limite_Cupos;
    String precio_taller;
    String estado;

    String nombre_docente;
    String descripcion_docente;
    String foto_docente;

    public Taller() {
    }

    public Taller(String id_taller, String nombre_taller, String descripcion_taller, String dia_taller, String hora_taller, String lugar, String genero, String photo_taller, String id_docente, String cant_Inscritos, String cant_Limite_Cupos, String precio_taller, String estado, String nombre_docente, String descripcion_docente, String foto_docente) {
        this.id_taller = id_taller;
        this.nombre_taller = nombre_taller;
        this.descripcion_taller = descripcion_taller;
        this.dia_taller = dia_taller;
        this.hora_taller = hora_taller;
        this.lugar = lugar;
        this.genero = genero;
        this.photo_taller = photo_taller;
        this.id_docente = id_docente;
        this.cant_Inscritos = cant_Inscritos;
        this.cant_Limite_Cupos = cant_Limite_Cupos;
        this.precio_taller = precio_taller;
        this.estado = estado;
        this.nombre_docente = nombre_docente;
        this.descripcion_docente = descripcion_docente;
        this.foto_docente = foto_docente;
    }

    public String getId_taller() {
        return id_taller;
    }

    public void setId_taller(String id_taller) {
        this.id_taller = id_taller;
    }

    public String getNombre_taller() {
        return nombre_taller;
    }

    public void setNombre_taller(String nombre_taller) {
        this.nombre_taller = nombre_taller;
    }

    public String getDescripcion_taller() {
        return descripcion_taller;
    }

    public void setDescripcion_taller(String descripcion_taller) {
        this.descripcion_taller = descripcion_taller;
    }

    public String getDia_taller() {
        return dia_taller;
    }

    public void setDia_taller(String dia_taller) {
        this.dia_taller = dia_taller;
    }

    public String getHora_taller() {
        return hora_taller;
    }

    public void setHora_taller(String hora_taller) {
        this.hora_taller = hora_taller;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPhoto_taller() {
        return photo_taller;
    }

    public void setPhoto_taller(String photo_taller) {
        this.photo_taller = photo_taller;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public String getCant_Inscritos() {
        return cant_Inscritos;
    }

    public void setCant_Inscritos(String cant_Inscritos) {
        this.cant_Inscritos = cant_Inscritos;
    }

    public String getCant_Limite_Cupos() {
        return cant_Limite_Cupos;
    }

    public void setCant_Limite_Cupos(String cant_Limite_Cupos) {
        this.cant_Limite_Cupos = cant_Limite_Cupos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre_docente() {
        return nombre_docente;
    }

    public void setNombre_docente(String nombre_docente) {
        this.nombre_docente = nombre_docente;
    }

    public String getDescripcion_docente() {
        return descripcion_docente;
    }

    public void setDescripcion_docente(String descripcion_docente) {
        this.descripcion_docente = descripcion_docente;
    }

    public String getFoto_docente() {
        return foto_docente;
    }

    public void setFoto_docente(String foto_docente) {
        this.foto_docente = foto_docente;
    }

    public String getPrecio_taller() {
        return precio_taller;
    }

    public void setPrecio_taller(String precio_taller) {
        this.precio_taller = precio_taller;
    }

    public static final Comparator<Taller> By_TITLE_ASCENDING = new Comparator<Taller>() {

        @Override
        public int compare(Taller o1, Taller o2) {
            return o1.getNombre_taller().compareTo(o2.getNombre_taller());
        }
    };

    public static final Comparator<Taller> By_TITLE_DESCENDING = new Comparator<Taller>() {
        @Override
        public int compare(Taller o1, Taller o2) {
            return o2.getNombre_taller().compareTo(o1.getNombre_taller());
        }
    };


}
