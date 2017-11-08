import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Vuelo {

    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;
    private LocalDate fechaSalida;
    private Avion avion;
    private int codigoDeVuelo;
    private Map<Asiento, Boolean> ocupacion = new HashMap<>();
    private LocalDateTime horarioSalida;
    List<PersonalAbordo> listaPersonalAbordo = new ArrayList<>();

    public Vuelo(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, int dia, int mes, int ano, int hora, int minutos, Avion avion, int codigoDeVuelo) {
        this.fechaSalida = LocalDate.of(ano,mes,dia);
        this.horarioSalida = fechaSalida.atTime(hora, minutos);
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.avion = avion;
        this.codigoDeVuelo = codigoDeVuelo;
        for (Asiento a : avion.getAsientos()) {
            ocupacion.put(a, false);
        }
        //validarVueloPorCantidadDePersonas();
    }

    public String getUbicacionSalida() {
        return aeropuertoSalida.getUbicacion();
    }

    public String getUbicacionLlegada() {
        return aeropuertoLlegada.getUbicacion();
    }

    public LocalDate getFechaSalida() {

        return fechaSalida;
    }

    public int getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public int cantidadAsientosDisponibles() {
        int result = 0;
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {

        String result = "Fecha: " + fechaSalida.getMonthValue() + "/" + (fechaSalida.getDayOfMonth()  + "/" + fechaSalida.getYear() +", Horario De Salida: "+ horarioSalida.getHour() +":" + horarioSalida.getMinute() +",  Desde: " + aeropuertoSalida.getUbicacion() + ",  Hasta: " + aeropuertoLlegada.getUbicacion()
                + ", Codigo: " + codigoDeVuelo);

        return result;
    }

    public void ocupar(Asiento asiento) {
        ocupacion.put(asiento, true);
    }

    public List<Asiento> asientosDisponibles() {
        List<Asiento> asientosDisponibles = new ArrayList<>();
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a) ) {
                asientosDisponibles.add(a);
            }
        }
        return asientosDisponibles;
    }

    public Asiento getAsiento(int fila, char columna){
        for (Asiento asiento: avion.getAsientos()) {
            if(asiento.getFila() == (fila) && asiento.getColumna() == columna){
                return asiento;
            }
        }
        throw new RuntimeException("El avion asignado al vuelo no cuenta con ese asiento");
    }

    public boolean getOcupacion(Asiento asiento) {
        return ocupacion.get(asiento);
    }

    public void addPersonalAbordo(PersonalAbordo personalAbordo){
        listaPersonalAbordo.add(personalAbordo);
    }

    public List<PersonalAbordo> getListaPersonalAbordo() {
        return listaPersonalAbordo;
    }

    public void validarVueloPorCantidadDePersonas(){
//        if(avion.getTipoDeAvion().getCantidadDePersonalAbordo() <= listaPersonalAbordo.size()){
//            return;
//        }
//        throw new RuntimeException("Cantidad de personal abordo es mayor a la capacidad del tipo de avion");
    }
}
