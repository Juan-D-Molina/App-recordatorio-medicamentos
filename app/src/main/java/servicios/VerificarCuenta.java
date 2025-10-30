package servicios;

import modelo.Paciente;

// Asociación: usa Paciente para verificar datos
public class VerificarCuenta {
    private Paciente paciente;

    public VerificarCuenta(Paciente paciente) {
        this.paciente = paciente;
    }

    public void verificar(String id, String clave) {
        System.out.println("Verificacion completada para: " + paciente.getNombre());
        System.out.println("ID ingresado: " + id);
        System.out.println("Clave ingresada: " + clave);
        System.out.println("(Simulacion de inicio de sesion)");
    }
}
