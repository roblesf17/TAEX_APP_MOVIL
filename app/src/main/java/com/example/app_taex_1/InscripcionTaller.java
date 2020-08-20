package com.example.app_taex_1;

public class InscripcionTaller {
    String id_inscripcion;
    String fecha_inscripcion;
    String tipo_taller;
    String nombre_taller;
    String nota_inscripcion;
    String estado_inscripcion;
    String precio_inscripcion;

    String nombre_alumnoI;
    String codigo_alumnoI;
    String domicilio_alumnoI;
    String celular_alumnoI;
    String email_alumnoI;
    String facultad_alumnoI;
    String escuela_alumnoI;

    public InscripcionTaller() {

    }

    public InscripcionTaller(String fecha_inscripcion, String tipo_taller, String nombre_taller, String nota_inscripcion, String estado_inscripcion, String precio_inscripcion, String nombre_alumnoI, String codigo_alumnoI, String domicilio_alumnoI, String celular_alumnoI, String email_alumnoI, String facultad_alumnoI, String escuela_alumnoI) {
        this.fecha_inscripcion = fecha_inscripcion;
        this.tipo_taller = tipo_taller;
        this.nombre_taller = nombre_taller;
        this.nota_inscripcion = nota_inscripcion;
        this.estado_inscripcion = estado_inscripcion;
        this.precio_inscripcion = precio_inscripcion;
        this.nombre_alumnoI = nombre_alumnoI;
        this.codigo_alumnoI = codigo_alumnoI;
        this.domicilio_alumnoI = domicilio_alumnoI;
        this.celular_alumnoI = celular_alumnoI;
        this.email_alumnoI = email_alumnoI;
        this.facultad_alumnoI = facultad_alumnoI;
        this.escuela_alumnoI = escuela_alumnoI;
    }

    public String getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(String id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public String getNota_inscripcion() {
        return nota_inscripcion;
    }

    public void setNota_inscripcion(String nota_inscripcion) {
        this.nota_inscripcion = nota_inscripcion;
    }

    public String getEstado_inscripcion() {
        return estado_inscripcion;
    }

    public void setEstado_inscripcion(String estado_inscripcion) {
        this.estado_inscripcion = estado_inscripcion;
    }

    public String getTipo_taller() {
        return tipo_taller;
    }

    public void setTipo_taller(String tipo_taller) {
        this.tipo_taller = tipo_taller;
    }

    public String getNombre_taller() {
        return nombre_taller;
    }

    public void setNombre_taller(String nombre_taller) {
        this.nombre_taller = nombre_taller;
    }

    public String getPrecio_inscripcion() {
        return precio_inscripcion;
    }

    public void setPrecio_inscripcion(String precio_inscripcion) {
        this.precio_inscripcion = precio_inscripcion;
    }

    public String getNombre_alumnoI() {
        return nombre_alumnoI;
    }

    public void setNombre_alumnoI(String nombre_alumnoI) {
        this.nombre_alumnoI = nombre_alumnoI;
    }

    public String getCodigo_alumnoI() {
        return codigo_alumnoI;
    }

    public void setCodigo_alumnoI(String codigo_alumnoI) {
        this.codigo_alumnoI = codigo_alumnoI;
    }

    public String getDomicilio_alumnoI() {
        return domicilio_alumnoI;
    }

    public void setDomicilio_alumnoI(String domicilio_alumnoI) {
        this.domicilio_alumnoI = domicilio_alumnoI;
    }

    public String getCelular_alumnoI() {
        return celular_alumnoI;
    }

    public void setCelular_alumnoI(String celular_alumnoI) {
        this.celular_alumnoI = celular_alumnoI;
    }

    public String getEmail_alumnoI() {
        return email_alumnoI;
    }

    public void setEmail_alumnoI(String email_alumnoI) {
        this.email_alumnoI = email_alumnoI;
    }

    public String getFacultad_alumnoI() {
        return facultad_alumnoI;
    }

    public void setFacultad_alumnoI(String facultad_alumnoI) {
        this.facultad_alumnoI = facultad_alumnoI;
    }

    public String getEscuela_alumnoI() {
        return escuela_alumnoI;
    }

    public void setEscuela_alumnoI(String escuela_alumnoI) {
        this.escuela_alumnoI = escuela_alumnoI;
    }
}
