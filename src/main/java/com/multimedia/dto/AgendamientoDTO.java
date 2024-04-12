package com.multimedia.dto;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class AgendamientoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String ordenConsulta;
    private String turno;
    private String sala;
    private String fechaHoraConsulta;
    private short estado;
    private String especialidad;
    private String medico;
    private String paciente;


    public enum EstadoAgendamiento {

        REGISTRADO((short) 1, "Registrado SC", new Color(255, 255, 128)),
        CONFIRMADO((short) 2, "En espera", new Color(104, 232, 158)),
        PAGADO((short) 3, "Cobrado", new Color(104, 232, 158)),
        FINALIZADO((short) 4, "Finalizado", new Color(255, 91, 50)),
        FINALIZADO_PROCED((short) 5, "Finalizado - FP", new Color(248, 208, 107)),
        CANCELADO((short) 6, "Cancelado", new Color(255, 91, 50));

        private final short val;
        private final String descripcion;
        private final Color colorEstado;

        private EstadoAgendamiento(short val, String descripcion, Color colorEstado) {
            this.val = val;
            this.descripcion = descripcion;
            this.colorEstado = colorEstado;
        }

        public short getVal() {
            return val;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public Color getColorEstado() {
            return colorEstado;
        }

        @Override
        public String toString() {
            return descripcion;
        }


    }

    public AgendamientoDTO() {
    }

    public AgendamientoDTO(Long id, String ordenConsulta, String turno, String sala, String fechaHoraConsulta, short estado, String especialidad, String medico, String paciente) {
        this.id = id;
        this.ordenConsulta = ordenConsulta;
        this.turno = turno;
        this.sala = sala;
        this.fechaHoraConsulta = fechaHoraConsulta;
        this.estado = estado;
        this.especialidad = especialidad;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdenConsulta() {
        return ordenConsulta;
    }

    public void setOrdenConsulta(String ordenConsulta) {
        this.ordenConsulta = ordenConsulta;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }



    public String getFechaHoraConsulta() {
        return fechaHoraConsulta;
    }

    public void setFechaHoraConsulta(String fechaHoraConsulta) {
        this.fechaHoraConsulta = fechaHoraConsulta;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }



    @Override
    public String toString() {
        return "AgendamientosDTO[ id=" + id + " ]";
    }

}
