import Cars.CarShowroom;
import Cars.CarShowroomContainer;
import Cars.ItemCondition;
import Cars.Vehicle;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GuiClass {

    private JFrame appFrame;
    private CarShowroomContainer classCarShowroomContainer;

    private TableModel carShowroomTableModel;
    private final String[] carShowroomColumnNames = {"Nazwa salonu", "Max ilość pojazdów", "Obecna ilość"};
    private JTable carShowroomJTable;
    private JScrollPane carShowroomJScrollPane;


    private TableModel vehicleTableModel;
    private final String[] vehicleColumnNames = {"Ilość", "Marka", "Model"};
    private JTable vehicleJTable;
    private JScrollPane vehicleJScrollPane;

    private JPanel buttonPanel;
    private JPanel messagePanel;

    private JTextField carShowroomNameTextField = new JTextField("Enter salon name", 13);
    private JTextField carShowroomSizeTextField = new JTextField("0", 13);

    private JTextField vehicleBrandTextField = new JTextField("Enter brand", 13);
    private JTextField vehicleModelTextField = new JTextField("Enter model", 13);

    private JLabel messageLabel = new JLabel("Hello!", SwingConstants.CENTER);

    public GuiClass(CarShowroomContainer carShowroomContainer){
        classCarShowroomContainer = carShowroomContainer;
        prepareGUI();
    }
    private void  prepareGUI(){
        createModels();
        appFrame = new JFrame("Cars");
        //appFrame.setSize(1200,550);
        appFrame.setLayout(new GridBagLayout());
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();
        //JButton button;

        //button = new JButton("Button 1");
        c.fill = GridBagConstraints.BOTH;
        //c.anchor = GridBagConstraints.LINE_START; //bottom of space

        c.weightx = 1;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        appFrame.add(carShowroomJScrollPane, c);

        //button = new JButton("Button 2");

        c.fill = GridBagConstraints.BOTH;
        //c.anchor = GridBagConstraints.CENTER; //bottom of space
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        appFrame.add(vehicleJScrollPane, c);

        //button = new JButton("Button 3");
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.anchor = GridBagConstraints.LINE_END; //bottom of space
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        appFrame.add(buttonPanel, c);

        //button = new JButton("4");
        messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;       //reset to default
        c.weightx = 0.0;
        c.weighty = 0.1;   //request any extra vertical space
        //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        //c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 2;       //third row
        appFrame.add(messagePanel, c);

        appFrame.pack();
        appFrame.setVisible(true);
    }
    public void createModels(){
        carShowroomTableModel = new AbstractTableModel() {
            public String getColumnName(int index) { return carShowroomColumnNames[index]; }
            public int getColumnCount() { return carShowroomColumnNames.length; }
            public int getRowCount() { return classCarShowroomContainer.getSalons().size();}
            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return classCarShowroomContainer.getCarShowroomsList().get(row).getShowroomName();
                } else if (col == 1) {
                    return classCarShowroomContainer.getCarShowroomsList().get(row).getMaxCapacity();
                } else if (col == 2) {
                    return classCarShowroomContainer.getCarShowroomsList().get(row).getCurrentSize();
                }
                return "error";
            }


        };
        carShowroomJTable = new JTable(carShowroomTableModel);
        carShowroomJTable.addMouseListener(new TableMouseListener());
        carShowroomJScrollPane = new JScrollPane(carShowroomJTable);


        vehicleTableModel = new AbstractTableModel() {
            public String getColumnName(int index) { return vehicleColumnNames[index]; }
            public int getColumnCount() { return vehicleColumnNames.length; }
            public int getRowCount() { return 0;}
            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return "not initialized";
                } else if (col == 1) {
                    return "not initialized";
                } else if (col == 2) {
                    return "not initialized";
                }
                return "error";
            }
        };
        vehicleJTable = new JTable(vehicleTableModel);
        vehicleJScrollPane = new JScrollPane(vehicleJTable);
    }

    public void displayApplication(){
        JButton listVehiclesButton = new JButton("List cars");
        listVehiclesButton.addActionListener(new JScrollPaneListener());

        JButton deleteCarShowroom = new JButton("Delete salon");
        deleteCarShowroom.addActionListener(new JScrollPaneListener());

        JButton deleteVehicle = new JButton("Delete car");
        deleteVehicle.addActionListener(new JScrollPaneListener());

        JButton addCarShowroom = new JButton("Add salon");
        addCarShowroom.addActionListener(new JScrollPaneListener());

        JButton addVehicle = new JButton("Add car");
        addVehicle.addActionListener(new JScrollPaneListener());

        JButton sortCarShowrooms = new JButton("Sort salons");
        sortCarShowrooms.setActionCommand("Sort Centers");
        sortCarShowrooms.addActionListener(new JScrollPaneListener());

        JLabel addCarShowroomLabel = new JLabel("Add new salon: ");
        buttonPanel.add(addCarShowroomLabel);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(carShowroomNameTextField);
        buttonPanel.add(Box.createVerticalStrut(5));
        JLabel addCarShowroomSizeLabel = new JLabel("Enter size of salon: ");
        buttonPanel.add(addCarShowroomSizeLabel);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(carShowroomSizeTextField);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(addCarShowroom);
        buttonPanel.add(Box.createVerticalStrut(20));


        JLabel addVehicleLabel = new JLabel("Add new car: ");
        buttonPanel.add(addVehicleLabel);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(vehicleBrandTextField);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(vehicleModelTextField);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(addVehicle);
        buttonPanel.add(Box.createVerticalStrut(20));



        buttonPanel.add(deleteCarShowroom);
        buttonPanel.add(deleteVehicle);
        buttonPanel.add(listVehiclesButton);
        buttonPanel.add(sortCarShowrooms);


        //errMessageLabel.setForeground(Color.RED);
        messagePanel.add(messageLabel);
    }

    private class TableMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int carShowroomSelectedRow = carShowroomJTable.getSelectedRow();
            List<CarShowroom> carShowroomsList = classCarShowroomContainer.getCarShowroomsList();
            CarShowroom carShowroom = carShowroomsList.get(carShowroomSelectedRow);


            vehicleTableModel = new AbstractTableModel() {
                public String getColumnName(int index) {
                    return vehicleColumnNames[index];
                }

                public int getColumnCount() {
                    return vehicleColumnNames.length;
                }

                public int getRowCount() {
                    return carShowroom.getCarList().size();
                }

                public Object getValueAt(int row, int col) {
                    List<Vehicle> listOfCars = carShowroom.mapToArrayList();
                    if (col == 0) {
                        return carShowroom.getCarList().get(listOfCars.get(row));
                    } else if (col == 1) {
                        return listOfCars.get(row).getBrand();
                    } else if (col == 2) {
                        return listOfCars.get(row).getModel();
                    }
                    return "error";
                }
            };

            vehicleJTable = new JTable(vehicleTableModel);
            vehicleJScrollPane.getViewport().add(vehicleJTable);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class JScrollPaneListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int carShowroomSelectedRow = carShowroomJTable.getSelectedRow();
            int vehicleSelectedRow = vehicleJTable.getSelectedRow();
            JButton source = (JButton) e.getSource();
            if ("List cars".equals(source.getText())) {
                if (carShowroomSelectedRow == -1) {
                    messageLabel.setText("CHOOSE A SALON TO LIST");
                } else {
                    List<CarShowroom> carShowroomsList = classCarShowroomContainer.getCarShowroomsList();
                    CarShowroom carShowroom = carShowroomsList.get(carShowroomSelectedRow);


                    vehicleTableModel = new AbstractTableModel() {
                        public String getColumnName(int index) {
                            return vehicleColumnNames[index];
                        }

                        public int getColumnCount() {
                            return vehicleColumnNames.length;
                        }

                        public int getRowCount() {
                            return carShowroom.getCarList().size();
                        }

                        public Object getValueAt(int row, int col) {
                            List<Vehicle> listOfCars = carShowroom.mapToArrayList();
                            if (col == 0) {
                                return carShowroom.getCarList().get(listOfCars.get(row));
                            } else if (col == 1) {
                                return listOfCars.get(row).getBrand();
                            } else if (col == 2) {
                                return listOfCars.get(row).getModel();
                            }
                            return "error";
                        }
                    };

                    vehicleJTable = new JTable(vehicleTableModel);
                    vehicleJScrollPane.getViewport().add(vehicleJTable);
                }
            }
            else if ("Add salon".equals(source.getText())) {
                String name = carShowroomNameTextField.getText();
                int size = Integer.parseInt(carShowroomSizeTextField.getText());
                classCarShowroomContainer.addCenter(name, size);

                carShowroomTableModel = new AbstractTableModel() {
                    public String getColumnName(int index) { return carShowroomColumnNames[index]; }
                    public int getColumnCount() { return carShowroomColumnNames.length; }
                    public int getRowCount() { return classCarShowroomContainer.getSalons().size();}
                    public Object getValueAt(int row, int col) {
                        if (row < classCarShowroomContainer.getSalons().size()) {
                            if (col == 0) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getShowroomName();
                            } else if (col == 1) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getMaxCapacity();
                            } else if (col == 2) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getCurrentSize();
                            }
                        }
                        return "error";
                    }
                };
                carShowroomJTable = new JTable(carShowroomTableModel);
                carShowroomJTable.addMouseListener(new TableMouseListener());
                carShowroomJScrollPane.getViewport().add(carShowroomJTable);
            }
            else if ("Delete salon".equals(source.getText())) {
                if (carShowroomSelectedRow == -1) {
                    messageLabel.setText("CHOOSE SALON YOU WANT TO REMOVE");
                } else {
                    String carShowroomName = classCarShowroomContainer.getCarShowroomsList().get(carShowroomSelectedRow).getShowroomName();
                    classCarShowroomContainer.removeCenter(carShowroomName);

                    carShowroomTableModel = new AbstractTableModel() {
                        public String getColumnName(int index) { return carShowroomColumnNames[index]; }
                        public int getColumnCount() { return carShowroomColumnNames.length; }
                        public int getRowCount() { return classCarShowroomContainer.getSalons().size();}
                        public Object getValueAt(int row, int col) {
                            if (col == 0) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getShowroomName();
                            } else if (col == 1) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getMaxCapacity();
                            } else if (col == 2) {
                                return classCarShowroomContainer.getCarShowroomsList().get(row).getCurrentSize();
                            }
                            return "error";
                        }
                    };

                    carShowroomJTable = new JTable(carShowroomTableModel);
                    carShowroomJTable.addMouseListener(new TableMouseListener());
                    carShowroomJScrollPane.getViewport().add(carShowroomJTable);;
                }
            }
            else if ("Add car".equals(source.getText())) {
                if (carShowroomSelectedRow == -1) {
                    messageLabel.setText("CHOOSE SALON TO ADD A NEW CAR");
                } else {
                    String brand = vehicleBrandTextField.getText();
                    String model = vehicleModelTextField.getText();
                    Vehicle vehicle = new Vehicle(brand, model, ItemCondition.NEW,2003,10000,27000,1.5);

                    CarShowroom carShowroom = classCarShowroomContainer.getCarShowroomsList().get(carShowroomSelectedRow);

                    carShowroom.addProduct(vehicle);

                    vehicleTableModel = new AbstractTableModel() {
                        public String getColumnName(int index) { return vehicleColumnNames[index]; }
                        public int getColumnCount() { return vehicleColumnNames.length; }
                        public int getRowCount() { return classCarShowroomContainer.getCarShowroomsList().get(carShowroomSelectedRow).getCarList().size();}
                        public Object getValueAt(int row, int col) {
                            List<Vehicle> listOfCars = carShowroom.mapToArrayList();
                            if (col == 0) {
                                return carShowroom.getCarList().get(listOfCars.get(row));
                            } else if (col == 1) {
                                return listOfCars.get(row).getBrand();
                            } else if (col == 2) {
                                return listOfCars.get(row).getModel();
                            }
                            return "error";
                        }
                    };

                    vehicleJTable = new JTable(vehicleTableModel);
                    vehicleJScrollPane.getViewport().add(vehicleJTable);
                }
            }
            else if ("Delete car".equals(source.getText())) {
                if (vehicleSelectedRow == -1 && carShowroomSelectedRow == -1) {
                    messageLabel.setText("CHOOSE SALON AND CAR YOU WANT TO REMOVE");
                } else if (vehicleSelectedRow == -1) {
                    messageLabel.setText("CHOOSE CAR YOU WANT TO REMOVE");
                } else {
                    String carShowroomName = classCarShowroomContainer.getCarShowroomsList().get(carShowroomSelectedRow).getShowroomName();
                    CarShowroom carShowroom = classCarShowroomContainer.getSalons().get(carShowroomName);


                    List<Vehicle> listOfCars = carShowroom.mapToArrayList();
                    Vehicle vehicle = listOfCars.get(vehicleSelectedRow);
                    carShowroom.removeProduct(vehicle);

                    vehicleTableModel = new AbstractTableModel() {
                        public String getColumnName(int index) { return vehicleColumnNames[index]; }
                        public int getColumnCount() { return vehicleColumnNames.length; }
                        public int getRowCount() { return carShowroom.getCarList().size();}
                        public Object getValueAt(int row, int col) {
                            List<Vehicle> updatedlistOfCars = carShowroom.mapToArrayList();
                            if (col == 0) {
                                return carShowroom.getCarList().get(updatedlistOfCars.get(row));
                            } else if (col == 1) {
                                return updatedlistOfCars.get(row).getBrand();
                            } else if (col == 2) {
                                return updatedlistOfCars.get(row).getModel();
                            }
                            return "error";
                        }
                    };

                    vehicleJTable = new JTable(vehicleTableModel);
                    vehicleJScrollPane.getViewport().add(vehicleJTable);
                }


            }
            else if("Sort salons".equals(source.getText())){
                List<CarShowroom> carShowroomsList = classCarShowroomContainer.getCarShowroomsList();
                carShowroomsList.sort(CarShowroom::compareTo);

                carShowroomTableModel = new AbstractTableModel() {
                    public String getColumnName(int index) { return carShowroomColumnNames[index]; }
                    public int getColumnCount() { return carShowroomColumnNames.length; }
                    public int getRowCount() { return classCarShowroomContainer.getSalons().size();}
                    public Object getValueAt(int row, int col) {
                        if (col == 0) {
                            return carShowroomsList.get(row).getShowroomName();
                        } else if (col == 1) {
                            return carShowroomsList.get(row).getMaxCapacity();
                        } else if (col == 2) {
                            return carShowroomsList.get(row).getCurrentSize();
                        }
                        return "error";
                    }
                };

                carShowroomJTable = new JTable(carShowroomTableModel);
                carShowroomJTable.addMouseListener(new TableMouseListener());
                carShowroomJScrollPane.getViewport().add(carShowroomJTable);

            }
        }
    }
}


