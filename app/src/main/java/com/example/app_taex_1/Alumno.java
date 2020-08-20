package com.example.app_taex_1;

public class Alumno {
    String codigo_alumno;
    String codigo;
    String nombre_alumno;
    String apellido_alummo;
    String fecha_nacimiento_alumno;
    String genero_alumno;
    String domicilio_alumno;
    String celular_alumno;
    String email_alumno;
    String facultad_alumno;
    String escuela_alumno;
    String contrasena_alumno;

    public Alumno() {
    }

    public Alumno(String codigo, String nombre_alumno, String apellido_alummo, String fecha_nacimiento_alumno, String genero_alumno, String domicilio_alumno, String celular_alumno, String email_alumno, String facultad_alumno, String escuela_alumno, String contrasena_alumno) {
        this.codigo = codigo;
        this.nombre_alumno = nombre_alumno;
        this.apellido_alummo = apellido_alummo;
        this.fecha_nacimiento_alumno = fecha_nacimiento_alumno;
        this.genero_alumno = genero_alumno;
        this.domicilio_alumno = domicilio_alumno;
        this.celular_alumno = celular_alumno;
        this.email_alumno = email_alumno;
        this.facultad_alumno = facultad_alumno;
        this.escuela_alumno = escuela_alumno;
        this.contrasena_alumno = contrasena_alumno;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo_alumno() {
        return codigo_alumno;
    }

    public void setCodigo_alumno(String codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alummo() {
        return apellido_alummo;
    }

    public void setApellido_alummo(String apellido_alummo) {
        this.apellido_alummo = apellido_alummo;
    }

    public String getFecha_nacimiento_alumno() {
        return fecha_nacimiento_alumno;
    }

    public void setFecha_nacimiento_alumno(String fecha_nacimiento_alumno) {
        this.fecha_nacimiento_alumno = fecha_nacimiento_alumno;
    }

    public String getGenero_alumno() {
        return genero_alumno;
    }

    public void setGenero_alumno(String genero_alumno) {
        this.genero_alumno = genero_alumno;
    }

    public String getDomicilio_alumno() {
        return domicilio_alumno;
    }

    public void setDomicilio_alumno(String domicilio_alumno) {
        this.domicilio_alumno = domicilio_alumno;
    }

    public String getCelular_alumno() {
        return celular_alumno;
    }

    public void setCelular_alumno(String celular_alumno) {
        this.celular_alumno = celular_alumno;
    }

    public String getEmail_alumno() {
        return email_alumno;
    }

    public void setEmail_alumno(String email_alumno) {
        this.email_alumno = email_alumno;
    }

    public String getFacultad_alumno() {
        return facultad_alumno;
    }

    public void setFacultad_alumno(String facultad_alumno) {
        this.facultad_alumno = facultad_alumno;
    }

    public String getEscuela_alumno() {
        return escuela_alumno;
    }

    public void setEscuela_alumno(String escuela_alumno) {
        this.escuela_alumno = escuela_alumno;
    }

    public String getContrasena_alumno() {
        return contrasena_alumno;
    }

    public void setContrasena_alumno(String contrasena_alumno) {
        this.contrasena_alumno = contrasena_alumno;
    }
}
