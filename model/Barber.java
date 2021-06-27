package model;

import javafx.application.Platform;
import util.Semaphore;
import view.ScreenView;

public class Barber extends Thread {
    
    public static int barberTurn = 0;
    
    public static final int CHAIRS = 5;
    public static Semaphore customer = new Semaphore(0);
    public static Semaphore barber = new Semaphore(0);
    public static Semaphore mutex = new Semaphore(1);
    // clientes estao esperando (nao sendo cortado)
    public static int waiting = 0;
    private boolean[] insideCastle = new boolean[5];
    
    /**
     * Construtor do barbeiro
     */
    public Barber() {
        for (int i = 0; i < insideCastle.length; i++) {
            insideCastle[i] = false;
        }
    }
    
    public void barbers() {
        while (true) {
            if (waiting <= 0) {
//                sleeping = true;
                sleep();
                customer.down(); // vai dormir se o numero de clientes for 0
            } else {
                mutex.down(); // entra na regiao critica
                waiting = waiting - 1; // one chair gets free
                barber.up(); // o barbeiro agora esta pronto para cortar
                mutex.up(); // sai da regiao critica
//                sleeping = false;
                wakeUp(); // acorda o barbeiro
                cutHair(); // corta o cabelo (fora da regiao critica)
            }
        }
    }
    
    @Override
    public void run() {
        while (true) {
            barbers();
        }
    }
    
    public void sleep() {
        System.out.println("Barbeiro esta dormindo ");
        
        if (!Customer.stillSitting[0] && !Customer.stillSitting[1] && !Customer.stillSitting[2] &&
                !Customer.stillSitting[3] && !Customer.stillSitting[4]) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.castleView.setVisible(false);
                    ScreenView.destroyedCastleView.setVisible(true);
                }
            });
        }
    }
    
    private void wakeUp() {
        System.out.println("Barbeiro acordou");
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ScreenView.castleView.setVisible(true);
                ScreenView.destroyedCastleView.setVisible(false);
            }
        });
    }
    
    private void cutHair() {
        barberTurn++;
        
        if (barberTurn > 5) {
            barberTurn = 1;
        }
        
        if (barberTurn == 1 &&
                !insideCastle[1] && !insideCastle[2] && !insideCastle[3] && !insideCastle[4]) {
            System.out.println("Mario (1) esta indo para o castelo");
            Customer.stillSitting[0] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView1.setX(Customer.MARIO_POSITION);
                }
            });
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView2.setVisible(false);
                    ScreenView.marioView1.setVisible(true);
                }
            });
            
            while (ScreenView.marioView1.getX() > 500) {
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
            
            System.out.println("Mario (1) entrou no castelo");
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView1.setVisible(false);
                    ScreenView.marioView3.setX(380);
                }
            });
            
            insideCastle[0] = true;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            // tempo cortando o cabelo
            try {
                sleep((long) (ScreenView.timeBarberSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView3.setVisible(true);
                }
            });
            
            System.out.println("Mario (1) esta saindo do castelo");
            
            while (ScreenView.marioView3.getX() >= 20) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.marioView3.setX(ScreenView.marioView3.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.marioView3.setVisible(false);
                }
            });
        } else if (barberTurn == 2 &&
                !insideCastle[0] && !insideCastle[2] && !insideCastle[3] && !insideCastle[4]) {
            System.out.println("Luigi (2) esta indo para o castelo");
            Customer.stillSitting[1] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView1.setX(Customer.LUIGI_POSITION);
                }
            });
            
            // levanta o cliente
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView2.setVisible(false);
                    ScreenView.luigiView1.setVisible(true);
                }
            });
            
            while (ScreenView.luigiView1.getX() > 500) {
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
            
            System.out.println("Luigi (2) entrou no castelo");
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView3.setX(380);
                    ScreenView.luigiView1.setVisible(false);
                }
            });
            
            insideCastle[0] = false;
            insideCastle[1] = true;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            // o tempo que o personagem ira ficar dentro do castelo
            try {
                sleep((long) (ScreenView.timeBarberSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView3.setVisible(true);
                }
            });
            
            System.out.println("Luigi (2) esta saindo do castelo");
            
            while (ScreenView.luigiView3.getX() >= 20) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.luigiView3.setX(ScreenView.luigiView3.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.luigiView3.setVisible(false);
                }
            });
        } else if (barberTurn == 3 &&
                !insideCastle[0] && !insideCastle[1] && !insideCastle[3] && !insideCastle[4]) {
            System.out.println("Toad (3) esta indo para o castelo");
            Customer.stillSitting[2] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView1.setX(Customer.TOAD_POSITION);
                }
            });
            
            // levanta o cliente
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView2.setVisible(false);
                    ScreenView.toadView1.setVisible(true);
                }
            });
            
            while (ScreenView.toadView1.getX() > 500) {
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
            
            System.out.println("Toad (3) entrou no castelo");
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView3.setX(380);
                    ScreenView.toadView1.setVisible(false);
                }
            });
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = true;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            // o tempo que o personagem ira ficar dentro do castelo
            try {
                sleep((long) (ScreenView.timeBarberSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView3.setVisible(true);
                }
            });
            
            System.out.println("Toad (3) esta saindo do castelo");
            
            // faz o personagem com visual mudado sair do cenario
            while (ScreenView.toadView3.getX() >= 20) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.toadView3.setX(ScreenView.toadView3.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.toadView3.setVisible(false);
                }
            });
        } else if (barberTurn == 4 &&
                !insideCastle[0] && !insideCastle[1] && !insideCastle[2] && !insideCastle[4]) {
            System.out.println("Yoshi (4) esta indo para o castelo");
            Customer.stillSitting[3] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView1.setX(Customer.YOSHI_POSITION);
                }
            });
            
            // levanta o cliente
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView2.setVisible(false);
                    ScreenView.yoshiView1.setVisible(true);
                }
            });
            
            // caminha ate entrar no castelo
            while (ScreenView.yoshiView1.getX() > 500) {
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
            
            System.out.println("Yoshi (4) entrou no castelo");
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView3.setX(320);
                    ScreenView.yoshiView1.setVisible(false);
                }
            });
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = true;
            insideCastle[4] = false;
            
            // tempo que o personagem fica dentro do castelo
            try {
                sleep((long) (ScreenView.timeBarberSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView3.setVisible(true);
                }
            });
            
            System.out.println("Yoshi (4) esta saindo do castelo");
            
            // faz o personagem com visual mudado sair do cenario
            while (ScreenView.yoshiView3.getX() >= 20) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.yoshiView3.setX(ScreenView.yoshiView3.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.yoshiView3.setVisible(false);
                }
            });
        } else if (barberTurn == 5 &&
                !insideCastle[0] && !insideCastle[1] && !insideCastle[2] && !insideCastle[3]) {
            System.out.println("Goomba (5) esta indo para o castelo");
            Customer.stillSitting[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView1.setX(Customer.GOOMBA_POSITION);
                }
            });
            
            // levanta o cliente
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView1.setVisible(true);
                    ScreenView.goombaView2.setVisible(false);
                }
            });
            
            // caminha ate entrar no castelo
            while (ScreenView.goombaView1.getX() > 500) {
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
            
            System.out.println("Goomba (5) entrou no castelo");
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView3.setX(380);
                    ScreenView.goombaView1.setVisible(false);
                }
            });
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = true;
            
            // tempo que o personagem fica dentro do castelo
            try {
                sleep((long) (ScreenView.timeBarberSld.getValue() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView3.setVisible(true);
                }
            });
            
            System.out.println("Goomba (5) esta saindo do castelo");
            
            // faz o personagem com visual mudado sair do cenario
            while (ScreenView.goombaView3.getX() >= 20) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.goombaView3.setX(ScreenView.goombaView3.getX() - 1);
                    }
                });
                
                try {
                    sleep((long) (52.5 - ScreenView.speedCustomerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            insideCastle[0] = false;
            insideCastle[1] = false;
            insideCastle[2] = false;
            insideCastle[3] = false;
            insideCastle[4] = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.goombaView3.setVisible(false);
                }
            });
        }
    } // fim do metodo cutHair
} // fim da classe Barbeiro
