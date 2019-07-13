package ve.com.cabelloisaac.myapplication;

public class Dias {

    private int id, dia, hora,  minuto,  horaEnd,  minutoEnd;

    public Dias(int id, int dia, int hora, int minuto, int horaEnd, int minutoEnd) {
        this.id=id;
        this.dia=dia;
        this.hora=hora;
        this.minuto=minuto;
        this.horaEnd=horaEnd;
        this.minutoEnd=minutoEnd;
    }

    public int getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getHoraEnd() {
        return horaEnd;
    }

    public int getMinutoEnd() {
        return minutoEnd;
    }
}
