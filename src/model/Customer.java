package model;

import javafx.application.Platform;
import view.ScreenView;

public class Customer extends Thread {
    
    private int customerTurn = 0;
    
    private final int START_POSITION = 1250;
    public static final int MARIO_POSITION = 680;
    public static final int LUIGI_POSITION = 770;
    public static final int TOAD_POSITION = 830;
    public static final int YOSHI_POSITION = 910;
    public static final int GOOMBA_POSITION = 960;
    public static boolean[] stillSitting = new boolean[5];
    
    void customer() {
        Barber.mutex.down(); // tenta ter acesso as cadeiras
        enterCustomer();
        if (Barber.waiting < Barber.CHAIRS) { // se nao houver cadeiras vazia, saia
            Barber.waiting = Barber.waiting + 1; // incrementa contador do waiting
            sitDown(); // senta na cadeira
            Barber.customer.up(); // notifica o barbeiro que tem um cliente
            Barber.mutex.up(); // nao precisa trancar as cadeiras mais
//            Barber.barber.down(); // vai dormir se o numero de barbeiros livres for zero
        } else {
            leaveCustomer(); // o cliente ira deixar a barbearia
            Barber.mutex.up(); // barbearia esta cheia, nao espera
        }
    }
    
    @Override
    public void run() {
        while (true) {
            customer();
            
            try {
                sleep((long) (ScreenView.timeCustomerSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void enterCustomer() {
        // define que entre um cliente diferente toda vez que executar o metodo
        customerTurn++;
        
        if (customerTurn > 5) {
            customerTurn = 1;
        }
        
        if (customerTurn == 1) {
            System.out.println("Mario (1) entrou na barbearia");
            ScreenView.marioView1.setX(START_POSITION);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView1.setVisible(true);
                }
            });
            
            while (ScreenView.marioView1.getX() >= MARIO_POSITION) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView1.setX(ScreenView.marioView1.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (stillSitting[0] == true) {
                leaveCustomer();
            }
        } else if (customerTurn == 2) {
            if (stillSitting[1] == false) {
                System.out.println("Luigi (2) entrou na barbearia");
                ScreenView.luigiView1.setX(START_POSITION);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.luigiView1.setVisible(true);
                    }
                });
                
                while (ScreenView.luigiView1.getX() >= LUIGI_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.luigiView1.setX(ScreenView.luigiView1.getX() - 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (customerTurn == 3) {
            System.out.println("Toad (3) entrou na barbearia");
            ScreenView.toadView1.setX(START_POSITION);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView1.setVisible(true);
                }
            });
            
            while (ScreenView.toadView1.getX() >= TOAD_POSITION) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.toadView1.setX(ScreenView.toadView1.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (stillSitting[2] == true) {
                leaveCustomer();
            }
        } else if (customerTurn == 4) {
            System.out.println("Yoshi (4) entrou na barbearia");
            ScreenView.yoshiView1.setX(START_POSITION);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView1.setVisible(true);
                }
            });
            
            while (ScreenView.yoshiView1.getX() >= YOSHI_POSITION) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.yoshiView1.setX(ScreenView.yoshiView1.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (stillSitting[3] == true) {
                leaveCustomer();
            }
        } else if (customerTurn == 5) {
            System.out.println("Goomba (5) entrou na barbearia");
            ScreenView.goombaView1.setX(START_POSITION);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView1.setVisible(true);
                }
            });
            
            while (ScreenView.goombaView1.getX() >= GOOMBA_POSITION) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.goombaView1.setX(ScreenView.goombaView1.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (stillSitting[4] == true) {
                leaveCustomer();
            }
        }
    } // fim do  metodo enterCustomer
    
    private void sitDown() {
        if (customerTurn == 1 && stillSitting[0] == false) {
            System.out.println("O Mario (1) sentou.");
            stillSitting[0] = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView2.setX(MARIO_POSITION);
                }
            });
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView1.setVisible(false);
                    ScreenView.marioView2.setVisible(true);
                }
            });
            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (customerTurn == 2 && stillSitting[1] == false) {
            System.out.println("O Luigi (2) sentou");
            stillSitting[1] = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView2.setX(LUIGI_POSITION);
                }
            });
            
            // o cliente senta
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView1.setVisible(false);
                    ScreenView.luigiView2.setVisible(true);
                }
            });
            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (customerTurn == 3 && stillSitting[2] == false) {
            System.out.println("O Toad (3) sentou");
            stillSitting[2] = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView2.setX(TOAD_POSITION);
                }
            });
            
            // o cliente senta
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView1.setVisible(false);
                    ScreenView.toadView2.setVisible(true);
                }
            });
            
            // tempo que cliente fica sentado quando o barbeiro chama
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (customerTurn == 4 && stillSitting[3] == false) {
            System.out.println("O Yoshi (4) sentou");
            stillSitting[3] = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView2.setX(YOSHI_POSITION);
                }
            });
            
            // o cliente senta
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView1.setVisible(false);
                    ScreenView.yoshiView2.setVisible(true);
                }
            });
            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (customerTurn == 5 && stillSitting[4] == false) {
            System.out.println("O Goomba (5) sentou");
            stillSitting[4] = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView2.setX(GOOMBA_POSITION);
                }
            });
            
            // o cliente senta
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView1.setVisible(false);
                    ScreenView.goombaView2.setVisible(true);
                }
            });
            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } // fim do metodo sitDown
    
    private void leaveCustomer() {
        if (customerTurn == 1) {
            if (stillSitting[0]) {
                System.out.println("Barbearia cheia: Mario (1) esta saindo");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView1.setX(MARIO_POSITION);
                    }
                });
                
                while (ScreenView.marioView1.getX() <= START_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.marioView1.setX(ScreenView.marioView1.getX() + 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView1.setVisible(false);
                    }
                });
            } else {
                System.out.println("O Mario (1) sentou.");
                stillSitting[0] = true;
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView2.setX(MARIO_POSITION);
                    }
                });
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView1.setVisible(false);
                        ScreenView.marioView2.setVisible(true);
                    }
                });
                
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (customerTurn == 2) {
            if (stillSitting[1]) {
                System.out.println("Barbearia cheia: Luigi (2) esta saindo");
                ScreenView.luigiView1.setX(LUIGI_POSITION);
                
                while (ScreenView.luigiView1.getX() <= START_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.luigiView1.setX(ScreenView.luigiView1.getX() + 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.luigiView1.setVisible(false);
                    }
                });
            } else {
                System.out.println("O Luigi (2) sentou");
                stillSitting[1] = true;
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.luigiView2.setX(LUIGI_POSITION);
                    }
                });
                
                // o cliente senta
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.luigiView1.setVisible(false);
                        ScreenView.luigiView2.setVisible(true);
                    }
                });
                
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (customerTurn == 3) {
            if (stillSitting[2]) {
                System.out.println("Barbearia cheia: Toad (3) esta saindo");
                ScreenView.toadView1.setX(TOAD_POSITION);
                
                while (ScreenView.toadView1.getX() <= START_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.toadView1.setX(ScreenView.toadView1.getX() + 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.toadView1.setVisible(false);
                    }
                });
            } else {
                stillSitting[3] = true;
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.toadView2.setX(TOAD_POSITION);
                    }
                });
                
                // o cliente senta
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.toadView1.setVisible(false);
                        ScreenView.toadView2.setVisible(true);
                    }
                });
                
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (customerTurn == 4) {
            if (stillSitting[3]) {
                System.out.println("Barbearia cheia: Yoshi (4) esta saindo");
                ScreenView.yoshiView1.setX(YOSHI_POSITION);
                
                while (ScreenView.yoshiView1.getX() <= START_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.yoshiView1.setX(ScreenView.yoshiView1.getX() + 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.yoshiView1.setVisible(false);
                    }
                });
            } else {
                System.out.println("O Toad (3) sentou");
                stillSitting[2] = true;
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.yoshiView2.setX(YOSHI_POSITION);
                    }
                });
                
                // o cliente senta
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.yoshiView1.setVisible(false);
                        ScreenView.yoshiView2.setVisible(true);
                    }
                });
                
                // tempo que cliente fica sentado quando o barbeiro chama
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (customerTurn == 5) {
            if (stillSitting[4]) {
                System.out.println("Barberia cheia: Goomba (5) esta saindo");
                ScreenView.goombaView1.setX(GOOMBA_POSITION);
                
                while (ScreenView.goombaView1.getX() <= START_POSITION) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ScreenView.goombaView1.setX(ScreenView.goombaView1.getX() + 1);
                        }
                    });
                    
                    try {
                        sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.goombaView1.setVisible(false);
                    }
                });
            } else {
                System.out.println("O Goomba (5) sentou");
                stillSitting[4] = true;
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.goombaView2.setX(GOOMBA_POSITION);
                    }
                });
                
                // o cliente senta
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.goombaView1.setVisible(false);
                        ScreenView.goombaView2.setVisible(true);
                    }
                });
                
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } // fim do else/if
    } // fim do metodo leaveCustomer
} // fim da classe Customer
