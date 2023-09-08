package universidadgrupo8.entidades;

public class Inscripcion {

    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private boolean anioMateria;

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Materia materia, boolean anioMateria) {
        this.alumno = alumno;
        this.materia = materia;
        this.anioMateria = anioMateria;
    }

    public Inscripcion(int idInscripcion, Alumno alumno, Materia materia, boolean anioMateria) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.anioMateria = anioMateria;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public boolean isAnioMateria() {
        return anioMateria;
    }

    public void setAnioMateria(boolean anioMateria) {
        this.anioMateria = anioMateria;
    }

    @Override
    public String toString() {
       String inscrip = idInscripcion+" "+alumno.getApellido()+", "+alumno.getNombre()+" "+materia.getNombre();
       return inscrip;
    }
    
    
}
