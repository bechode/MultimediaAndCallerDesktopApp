package com.multimedia.llamador;

import com.multimedia.dto.AgendamientoDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class LlamadorConsultaController implements Initializable {

    @FXML
    private Label consultorioLabel;

    @FXML
    private Label pacienteLabel;

    @FXML
    private Label turnoLabel;

    @FXML
    private ListView<AgendamientoDTO> agendamientosListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
