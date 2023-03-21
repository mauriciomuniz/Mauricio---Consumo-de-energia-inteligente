package teste.medidores;


public class Medidor {
    private float consumo;
    private String dataMedida;
    private String horario;

    public Medidor(float consumo, String dataMedida, String horario)
    {
        this.consumo=consumo;
        this.dataMedida=dataMedida;
        this.horario=horario;
    }

    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getdataMedida(){
        return dataMedida;
    }

    public void setdataMedida(String dataMedida) {
        this.dataMedida = dataMedida;
    }

    public float getconsumo(){
        return consumo;
    }

    public void setConsumo(int consumo){
        this.consumo=consumo;
    }

}
