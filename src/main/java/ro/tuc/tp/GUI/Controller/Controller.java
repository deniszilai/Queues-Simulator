package ro.tuc.tp.GUI.Controller;

import ro.tuc.tp.GUI.View.View;
import ro.tuc.tp.Strategy.SimulationManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
    View view;
    public Controller(){//se controleaza butonul de start
        this.view = new View();
        view.setVisible(true);
        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationManager go = new SimulationManager(Integer.parseInt(view.getSimTime()), Integer.parseInt(view.getMaxProc()),
                        Integer.parseInt(view.getMinProc()), Integer.parseInt(view.getNoQueues()), Integer.parseInt(view.getNoClients()),
                        Integer.parseInt(view.getMaxArr()), Integer.parseInt(view.getMinArr()));
                view.setVisible(false);
                Thread t = new Thread(go);
                t.start();
            }
        });
    }
}

