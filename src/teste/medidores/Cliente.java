package teste.medidores;

public class Cliente {
    private String id;
    private float consumoTotal;
    private boolean alerta = false;
    private Medidor[] historico;
    private Medidor[] historicoAlarme = new Medidor[2];
    public boolean ConsumoAlto = false;
    private String faturaDia;
    private String [] faturas = new String[15];
    
    public String[] getFaturas() {
        return faturas;
    }

    public void setFaturas(String[] faturas) {
        this.faturas = faturas;
    }

    public Cliente(String id, String medicaoDia) {
        this.id = id;
        this.consumoTotal = 0;
        this.historico= new Medidor[15];
        this.faturaDia = medicaoDia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(float consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

    public String getfaturaDia() {
        return faturaDia;
    }
    public void setfaturaDia(String faturaDia) {
        this.faturaDia = faturaDia;
    }

    public Medidor[] getHistorico() {
        return historico;
    }

    public void setHistorico(Medidor[] historico) {
        this.historico = historico;
    }


    
    public void ConsumoAtualiza(float consumo, String dataMedida, String horario) {
        Medidor medidor = new Medidor(consumo, dataMedida, horario);
        
        if (consumo > 125) {
            ConsumoAlto = true;
            historicoAlarme[0] = medidor;
        }
        
        //media consumo vezes 1.3 que é a média por pessoa na bahia
        if (consumo > mediaConsumo() * 1.3) {
            alerta = true;
            historicoAlarme[1] = medidor;
        }
        
        atualizaHistoricoMedidor(medidor);
        
        consumoTotal += consumo;
    }
    
    private float mediaConsumo() {
        int media = 0;
        int contador = 0;
        for (Medidor m : historico) {
            if (m != null) {
                media += m.getconsumo();
                contador++;
            }
        }
        return (contador != 0) ? (float) media / contador : 0;
    }
    
    private void atualizaHistoricoMedidor(Medidor medidor) {
        boolean adicionado = false;
        for (int i = 0; i < historico.length; i++) {
            if (historico[i] == null) {
                historico[i] = medidor;
                adicionado = true;
                break;
            }
        }
        if (!adicionado) {
            for (int i = 0; i < historico.length - 1; i++) {
                historico[i] = historico[i + 1];
            }
            historico[14] = medidor;
        }
    }
    


    
    public String alertaConsumoAlto() {
        if (ConsumoAlto) {
            ConsumoAlto = false;
            Medidor medidorAlarme = historicoAlarme[0];
            String dataMedida = medidorAlarme.getdataMedida();
            String horario = medidorAlarme.getHorario();
            float consumo = medidorAlarme.getconsumo();
            return String.format("Aviso\nConsumo excessivo:\nDia: %s Horario: %s\nConsumo: %.2fkW/h\n",
                    dataMedida, horario, consumo);
        }
        return "Sem consumo excessivo";
    }
    

    public String alertaVariacao() {
        if (alerta) {
            alerta = false;
            Medidor medidorAlarme = historicoAlarme[0];
            String dataMedida = medidorAlarme.getdataMedida();
            String horario = medidorAlarme.getHorario();
            float consumo = medidorAlarme.getconsumo();
            return String.format("Aviso\nVariacao de consumo\nDia: %s Horario: %s\nConsumo: %.2fkW/h\n",
                    dataMedida, horario, consumo);
        }
        return "Sem variacao de consumo";
    }
    
    
    

}

