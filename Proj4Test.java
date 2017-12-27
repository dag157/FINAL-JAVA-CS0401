package proj5;

//Made my Dominick Gurnari for CS0401 Mohinder Dick Project 5 SassySoccerAppV4 Sorry its a lot of code but the functionality is there

import java.awt.MenuItem;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu; //Imports most controls like Button and MenuBar
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import cs401.sassy.tracker.data.*;

/*
    A simple JavaFX application 
*/
public class Proj4Test extends Application {
	
public static Scanner scan = new Scanner(System.in);
	
	//all boolean values
	public static boolean VIPstatus = false;
	public static boolean running = true;
	public static boolean listCreated = false;
	public static boolean goalsSortDescending = true;
	public static boolean namesOrderAscending = true;
	public static boolean showSummary = true;
	public static boolean fileExists = false;
	public static boolean firstTime = true;
	public static boolean connect = true;
	
	public static String goalsSortStatus = "Descending";
	public static String namesSortStatus = "Ascending";
	public static String passwordEntry;
	public static String password = "#ChelseaIsTheBest";
	
	public static int goalstotal = 0;
	public static int goalstotalExternal = 0;
	public static int amountOfPlayers = 0;
	public static int amountOfPlayersRegular = 0;
	
	VBox sceneBoxUpdate;
	HBox topBoxUpdate;
	HBox topBoxConnect;
	Label UpdateLabel;
	Label ConnectLabel;
	Label DisconnectLabel;
	static ListView<String> UpdateListView;
	static ListView<String> ConnectListView;
	Button submitButtonUpdate;
	
	static Text textArea;
	
	static Stage updateStage = new Stage();
	
	//created Arraylist for easy sort and access
	public static ArrayList<SoccerPlayer> playerList = new ArrayList<SoccerPlayer>();
	public static ArrayList<User> playerListVIP = new ArrayList<User>();
	public static ArrayList<User> connectedVIPS = new ArrayList<User>();
	public static Scanner in = new Scanner(System.in);
	
    public void start(Stage primaryStage) {

        //Set the title of the scene (i.e. Window title)
        primaryStage.setTitle("Sassy Soocer App Version 4.0");

        Label passwordLabel = new Label("Please enter a password to continue as Vip, or "
        		+ "\npress enter to continue as a regular user");
        passwordLabel.setMinWidth(100);
        TextField passwordTextField = new TextField();
        passwordTextField.setMinWidth(100);
        
     // Create a button UI element
        Button btn = new Button();

        // Set the text, X & Y coordinates
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Enter");
        
        //SET UP STAGE AND SCENE AND PANE
        
        VBox passwordPane = new VBox(10, passwordLabel, passwordTextField);
        passwordPane.setAlignment(Pos.CENTER);
        passwordPane.getChildren().add(btn);
        
        Scene scene = new Scene(passwordPane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //PASSWORD BUTTON ACTION
        
        btn.setOnAction(e -> {
        	
        	passwordEntry = passwordTextField.getText();
            
            if(passwordEntry.equals(password)) {

            	textArea = new Text("");
            	
            	// Create Pane to host UI elements
                VBox pane = new VBox();
                
                // Create menu bar to host menu items
                MenuBar mainMenu = new MenuBar();

                // Create new menus to the bar
                Menu fileTab = new Menu("File");
                
                javafx.scene.control.MenuItem importPlayerListTab = new javafx.scene.control.MenuItem("Import Player List");
                javafx.scene.control.MenuItem importPrefencesTab = new javafx.scene.control.MenuItem("Import Preferences");
                javafx.scene.control.MenuItem savetab = new javafx.scene.control.MenuItem("Save All");
                javafx.scene.control.MenuItem exitMenuTab = new javafx.scene.control.MenuItem("Exit");
                
                Menu playerListTab = new Menu("Players");
                
                javafx.scene.control.MenuItem createNewListTab = new javafx.scene.control.MenuItem("Create New List");
                javafx.scene.control.MenuItem viewPlayerListTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem updatePlayerListTab = new javafx.scene.control.MenuItem("Update Players");
                javafx.scene.control.MenuItem removePlayersTab = new javafx.scene.control.MenuItem("Remove Players");
                
                Menu goalListTab = new Menu("Goal List");
                
                javafx.scene.control.MenuItem viewGoalsTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem addGoalsTab = new javafx.scene.control.MenuItem("Add Goals");
                
                Menu preferencesTab = new Menu("Prefences");
                
                javafx.scene.control.MenuItem viewPreferencesTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem updatePasswordTab = new javafx.scene.control.MenuItem("Update Password");
                javafx.scene.control.MenuItem connectToVIPTab = new javafx.scene.control.MenuItem("Connect to Others");
                javafx.scene.control.MenuItem disconnectVIPTab = new javafx.scene.control.MenuItem("Disconnect from Others");
                javafx.scene.control.MenuItem changeGoalSortTab = new javafx.scene.control.MenuItem("Change Goal Sort");
                javafx.scene.control.MenuItem changeNameSortTab = new javafx.scene.control.MenuItem("Change Name Sort");
                javafx.scene.control.MenuItem changeSummaryTab = new javafx.scene.control.MenuItem("Change Summary Display");
                javafx.scene.control.MenuItem saveVIPConnectionList = new javafx.scene.control.MenuItem("Save VIP Connections");
                
                
                Menu connectionsTab = new Menu("Connections");
                
                javafx.scene.control.MenuItem connectionsShowTab = new javafx.scene.control.MenuItem("Active Connections");
                 
                //NEW SCENE
                
                Scene sceneMain = new Scene(pane, 500, 650);
                primaryStage.setScene(sceneMain);
                primaryStage.show();
                
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);

                VBox vbox = new VBox(new Text("Sent to the VIP Menu"));
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(15));

                //SENT TO VIP MENU
                
                dialogStage.setScene(new Scene(vbox));
                dialogStage.show();
                
                //ACTION COMMANDS FOR EACH TAB
                
                createNewListTab.setOnAction(r -> {
                	if(listCreated == false) {
                		//SET AMOUNT OF PLAYERS
                		Label playerAmountLabel = new Label("Please enter the number of players that would like to track 8 max");
                		playerAmountLabel.setMinWidth(100);
                		TextField playerAmountTextField = new TextField();
                		playerAmountTextField.setMinWidth(100);
//
                		Button btnCreateList = new Button();
//
                		// Set the text, X & Y coordinates
                		btnCreateList.setLayoutX(100);
                		btnCreateList.setLayoutY(80);
                		btnCreateList.setText("Enter");
//
                		VBox createPane = new VBox(10, playerAmountLabel, playerAmountTextField);
                		createPane.setAlignment(Pos.CENTER);
                		createPane.getChildren().add(btnCreateList);

                		Stage stag2 = new Stage();
                		
                		Scene sceneCreatePlayerAmount = new Scene(createPane, 300, 250);
                		stag2.setScene(sceneCreatePlayerAmount);
                		stag2.show();
                		
                		btnCreateList.setOnAction(a -> {
                			
                			amountOfPlayers = Integer.parseInt(playerAmountTextField.getText());
                			System.out.println(amountOfPlayers);
                			stag2.close();
                			
                			//CREATES EMPTY FILE TO BEGIN STORING DATA IF FILE DOES NOT EXIST
    						try {
    							createEmptyFile();
    						} catch (FileNotFoundException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    						
    						if (amountOfPlayers <=8 && amountOfPlayers >0) {
    							
    							//WARNING
    							System.out.println("**** CAUTION ****\nCreating Players with duplicate names will\ncause your list to be deleted.\n");
    							Stage dialogStageWarning = new Stage();
    			                //dialogStageWarning.initModality(Modality.WINDOW_MODAL);

    			                VBox vboxWarning = new VBox(new Text("**** CAUTION ****\nCreating Players with duplicate names will\ncause your list to be deleted.\n"));
    			                vboxWarning.setAlignment(Pos.CENTER);
    			                vboxWarning.setPadding(new Insets(15));

    			                dialogStageWarning.setScene(new Scene(vboxWarning));
    			                dialogStageWarning.show();
    							
    							//CREATES PLAYER OBJECTS
    							for (int i =0; i < amountOfPlayers; i++) {
    								Label playerNameLabel = new Label("Please enter the name of player ");
    		                		playerNameLabel.setMinWidth(100);
    		                		TextField playerNameTextField = new TextField();
    		                		playerNameTextField.setMinWidth(100);
    		//
    		                		Button btnPlayerName = new Button();
    		//
    		                		// Set the text, X & Y coordinates
    		                		btnPlayerName.setLayoutX(100);
    		                		btnPlayerName.setLayoutY(80);
    		                		btnPlayerName.setText("Enter");
    		//
    		                		VBox createPlayerNamePane = new VBox(10, playerNameLabel, playerNameTextField);
    		                		createPlayerNamePane.getChildren().add(btnPlayerName);

    		                		Scene sceneCreatePlayerName = new Scene(createPlayerNamePane, 300, 250);
    		                		Stage playerNameSet = new Stage();
    		                		playerNameSet.setScene(sceneCreatePlayerName);
    		                		playerNameSet.show();
    		                		
    		                		btnPlayerName.setOnAction(v -> {
    		                			
    		                			playerNameSet.close();
    		                			String playerName = playerNameTextField.getText();
    		                			System.out.println(playerName);
    		                			SoccerPlayer player = new SoccerPlayer(playerName);
    									playerList.add(player);
    		                			
    		                		});
    		                		
    							}
    							
    							//LIST CREATED
    							listCreated = true;
    							//CHECK DUPLICATE VALUES
    							checkDuplicate();
    							
    						} else {
    							//CATCH
    							textArea.setText("You must enter a number between 0 and 8");
    						}
                		});
					} else {
						textArea.setText("You must delete a list first");
					}
                });
                
                //DISPLAYS PLAYERS
                viewPlayerListTab.setOnAction(r -> {
                	
                	if(listCreated == true) {
                			textArea.setText("");
                			String a = "";
                			SoccerPlayer empty = new SoccerPlayer("");
                			playerList.add(empty);
                			for(int u =0; u < amountOfPlayers; u++) {
                				a = a + playerList.get(u).getName() + "\n";
                				//textArea.setText("\n " + playerList.get(u).getName());
                				//pane.getChildren().add(new Text(playerList.get(u).getName()));
                			}
                			textArea.setText(a);
                			playerList.remove(empty);
                	} else {
                		textArea.setText("You must create a list first");
                		
                	}
                });
                
                //UPDATES PLAYERS WITH LISTVIEW
                
                updatePlayerListTab.setOnAction(x -> {
                	
                	//LISTVIEW
                	
                	if(listCreated == true) {  
                	  UpdateLabel = new Label("Choose a Player:");
                	  UpdateListView = new ListView<String>();
                	  for(int p = 0; p < amountOfPlayers; p++) {
                		  SoccerPlayer empty = new SoccerPlayer("");
                  		  playerList.add(empty);
                		  UpdateListView.getItems().addAll(playerList.get(p).getName());
                		  playerList.remove(empty);
                	  }
                	  
                	  topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
                	  topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
                	  topBoxUpdate.setAlignment(Pos.CENTER);
                	  
                	  submitButtonUpdate = new Button("SELECT");
                	  submitButtonUpdate.setOnAction(z -> updatePlayer());
                	  
                	  sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
                	  sceneBoxUpdate.setAlignment(Pos.CENTER);
                	  
                	  Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
                	  updateStage.setScene(sceneUpdate);
                	  updateStage.show();
                	} else {
                		textArea.setText("You must create a list first");
                	}
                });
                
                //LISTVIEW TO REMOVE PLAYERS ALSO
                
                removePlayersTab.setOnAction(y -> {
                	if(listCreated == true) {
                	UpdateLabel = new Label("Choose a Player:");
                	UpdateListView = new ListView<String>();
              	  	for(int p = 0; p < amountOfPlayers; p++) {
              		  UpdateListView.getItems().addAll(playerList.get(p).getName());
              	  	}
              	  	
              	    UpdateListView.getItems().addAll("Clear all");
              	  
              	  	topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
              	  	topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
              	  	topBoxUpdate.setAlignment(Pos.CENTER);
              	  
              	  	submitButtonUpdate = new Button("SELECT");
              	  	submitButtonUpdate.setOnAction(z -> removePlayer());
              	  
              	  	sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
              	  	sceneBoxUpdate.setAlignment(Pos.CENTER);
              	  
              	  	Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
              	  	updateStage.setScene(sceneUpdate);
              	  	updateStage.show();
                	} else {
                		textArea.setText("You must create a list first");
                	}
                });
                
                //VIEWS GOALS AND SUMMARY ETC
                
                viewGoalsTab.setOnAction(b -> {
                	if(listCreated == true) {
                		if(namesOrderAscending) {
							sortNames();
						} else {
							sortNamesDescending();
						}
						
						if(goalsSortDescending) {
							sortGoals();
						} else {
							sortGoalsAscending();
						}
						
						//TEXT AREA TEXT SET TO PREVENT MULTIPLE TEXT DISPLAY AT ONCE
            			textArea.setText("");
            			String a = "";
            			String c ="";
            			SoccerPlayer empty = new SoccerPlayer("");
            			playerList.add(empty);
            			for(int u =0; u < amountOfPlayers; u++) {
            				a = a + playerList.get(u).getName() + "       " + playerList.get(u).getgoals() + "\n";
            				//textArea.setText("\n " + playerList.get(u).getName());
            				//pane.getChildren().add(new Text(playerList.get(u).getName()));
            			}
            			
            			a = "Your list:\n" + a + "\n";
            			
            			if(showSummary) {
							a = a + showPlayerSummary() + "\n";
						} else {
							//nothing shown
						}
            			
            			
            				for(User u:connectedVIPS)
            		        {
            		            a += "\n" + ("User " + u.getName() + " with ID " + u.getUserID() + " players:\n\n");
            		            
            		            if(namesOrderAscending) {
        							sortNamesExternal(u.getUserID());
        						} else {
        							sortNamesDescendingExternal(u.getUserID());
        						}
            		            
            		            if(goalsSortDescending) {
            		            	sortGoalsExternal(u.getUserID());
            		            } else {
            		            	sortGoalsAscendingExternal(u.getUserID());
            		            }
            		            
            		            for(Sasser i:u.getSassers())
            		            {
            		                a += (i.getName() + " " + i.getGoals() + "\n");
            		            }
            		            
            		            
            		            if(showSummary) {
            		            	a += "\n" + showPlayerSummaryExternal(u.getUserID());
            		            }
            		            goalstotalExternal = 0;
            		            
            		        }
            				
            				
            			
            			
            			
            			
            			textArea.setText(a);
            			playerList.remove(empty);
            			goalstotal = 0;
            			
            	} else {
            		//CATCH
            		textArea.setText("you must make a list first");
            		
            	}
                });
                
                //ADDS GOALS WITH LISTVIEW
                addGoalsTab.setOnAction(x -> {
                	if(listCreated == true) {  
                  	  UpdateLabel = new Label("Choose a Player:");
                  	  UpdateListView = new ListView<String>();
                  	  for(int p = 0; p < amountOfPlayers; p++) {
                  		  SoccerPlayer empty = new SoccerPlayer("");
                    		  playerList.add(empty);
                  		  UpdateListView.getItems().addAll(playerList.get(p).getName());
                  		  playerList.remove(empty);
                  	  }
                  	  
                  	  topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
                  	  topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
                  	  topBoxUpdate.setAlignment(Pos.CENTER);
                  	  
                  	  submitButtonUpdate = new Button("SELECT");
                  	  submitButtonUpdate.setOnAction(z -> updatePlayerGoals());
                  	  
                  	  sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
                  	  sceneBoxUpdate.setAlignment(Pos.CENTER);
                  	  
                  	  Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
                  	  updateStage.setScene(sceneUpdate);
                  	  updateStage.show();
                  	} else {
                  		pane.getChildren().add(new Text("You must make a list first"));
                  	}
                });
                
                //DISPLAYS PREFERENCES
                viewPreferencesTab.setOnAction(d -> {
                	
            			textArea.setText("");
            			String a = "1. Goals sort order " + "[" + goalsSortStatus + "]"
							+ "\n2. Names sort order " + "[" + namesSortStatus + "]" + 
									"\n3. Show goal summary " + "[" + showSummary + "]"
									+ "\n4. Return to main menu\n";
            			
            			textArea.setText(a);
                });
                
                //CHANGE PREFERENCES
                changeGoalSortTab.setOnAction(w -> {
                	//ALERTS ARE A GOOD WAY TO CONFIRM
                	Alert confirm = 
            				new Alert(AlertType.CONFIRMATION, 
            				"Are you sure you do that?!!!");

            			Optional<ButtonType> response = confirm.showAndWait();

            			if(response.isPresent() && response.get() == ButtonType.CANCEL) {
            				System.out.println("You cancelled.");
            				
            			} else {
                	
		                	if(goalsSortDescending) {
								
								goalsSortDescending = false;
								goalsSortStatus = "Ascending";
								textArea.setText("Goals sort order set to " + goalsSortStatus);
								
							} else {
								
								goalsSortDescending = true;
								goalsSortStatus = "Descending";
								textArea.setText("Goals sort order set to " + goalsSortStatus);
							}
            			}
                });
                
                changeNameSortTab.setOnAction(c -> {
                	
                	Alert confirm = 
            				new Alert(AlertType.CONFIRMATION, 
            				"Are you sure you do that?!!!");

            			Optional<ButtonType> response = confirm.showAndWait();

            			if(response.isPresent() && response.get() == ButtonType.CANCEL) {
            				System.out.println("You cancelled.");
            				
            			} else {
                	
		                	if(namesOrderAscending) {
								
								namesOrderAscending = false;
								namesSortStatus = "Descending";
								textArea.setText("Name sort order set to " + namesSortStatus);
							} else {
								
								namesOrderAscending = true;
								namesSortStatus = "Ascending";
								textArea.setText("Name sort order set to " + namesSortStatus);
							}
            			}
                });
                
                changeSummaryTab.setOnAction(q -> {
                	
                	Alert confirm = 
            				new Alert(AlertType.CONFIRMATION, 
            				"Are you sure you do that?!!!");

            			Optional<ButtonType> response = confirm.showAndWait();

            			if(response.isPresent() && response.get() == ButtonType.CANCEL) {
            				System.out.println("You cancelled.");
            				
            			} else {
                	
		                	if(showSummary) {
								
								showSummary = false;
								textArea.setText("Show goal Summary set to " + showSummary);
								
							} else {
								
								showSummary = true;
								textArea.setText("Show goal Summary set to " + showSummary);
								
							}
            			}
                });
                
                //IMPORT PLAYERS
                importPlayerListTab.setOnAction(i -> {
                	try {
						readFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                
               //SAVES LIST AS IS
                savetab.setOnAction(l -> {
                	//CREATE EMPTY FILE TO STORE PLAYER AND GOAL VALUES
					try {
						createEmptyFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PLAYER NAMES AND GOALS
					try {
						appendFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//CREATE EMPTY PREFERENCE FILE
					try {
						createEmptyPreferenceFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PREFERENCES TO FILE
					try {
						appendPreferenceFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textArea.setText("Preferences and Files saved. Thank you");
                });
                
                //IMPORTS PREFERENCES
                importPrefencesTab.setOnAction(r -> {
                	try {
						readPreferenceFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                
                //EXITS AND SAVES FILES
                exitMenuTab.setOnAction(f -> {
                	try {
						createEmptyFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PLAYER NAMES AND GOALS
					try {
						appendFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//CREATE EMPTY PREFERENCE FILE
					try {
						createEmptyPreferenceFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PREFERENCES TO FILE
					try {
						appendPreferenceFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						createEmptyConnectedUsersFile();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						appendConnectedUsersFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	System.exit(0);
                });
                
                updatePasswordTab.setOnAction(k -> {
                	Label newPasswordLabel = new Label("Please enter your old password to continue");
            		newPasswordLabel.setMinWidth(100);
            		TextField newPasswordTextField = new TextField();
            		newPasswordTextField.setMinWidth(100);
//
            		Button btnCheckPassword = new Button();
//
            		// Set the text, X & Y coordinates
            		btnCheckPassword.setLayoutX(100);
            		btnCheckPassword.setLayoutY(80);
            		btnCheckPassword.setText("Enter");
//
            		VBox createPaneCheckPassword = new VBox(10, newPasswordLabel, newPasswordTextField);
            		createPaneCheckPassword.getChildren().add(btnCheckPassword);
            		createPaneCheckPassword.setAlignment(Pos.CENTER);

            		Stage stag4 = new Stage();
            		
            		Scene sceneCheckPassword = new Scene(createPaneCheckPassword, 300, 250);
            		stag4.setScene(sceneCheckPassword);
            		stag4.show();
            		
            		btnCheckPassword.setOnAction(p -> {
            			
            			if(newPasswordTextField.getText().equals(password)) {
            				
            				Label newPasswordEnterLabel = new Label("Please enter your new desired Password");
                    		newPasswordEnterLabel.setMinWidth(100);
                    		TextField newPasswordEnterTextField = new TextField();
                    		newPasswordEnterTextField.setMinWidth(100);
        //
                    		Button btnNewPassword = new Button();
        //
                    		// Set the text, X & Y coordinates
                    		btnNewPassword.setLayoutX(100);
                    		btnNewPassword.setLayoutY(80);
                    		btnNewPassword.setText("Enter");
        //
                    		VBox NewPane = new VBox(10, newPasswordEnterLabel, newPasswordEnterTextField);
                    		NewPane.getChildren().add(btnNewPassword);
                    		NewPane.setAlignment(Pos.CENTER);

                    		Stage stag3 = new Stage();
                    		
                    		Scene sceneNewPassword = new Scene(NewPane, 300, 250);
                    		stag3.setScene(sceneNewPassword);
                    		stag3.show();
            				
            				stag4.close();
            				
            				btnNewPassword.setOnAction(u -> {
            					password = newPasswordEnterTextField.getText();
            					System.out.print(password);

            					try {
									createBasicPasswordFile();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
            					
            					stag3.close();
            				});
            			} else {
            				Stage dialogStageTwo = new Stage();
                            dialogStage.initModality(Modality.WINDOW_MODAL);

                            VBox vboxTwo = new VBox(new Text("Incorrect Password"));
                            vboxTwo.setAlignment(Pos.CENTER);
                            vboxTwo.setPadding(new Insets(15));

                            //SENT TO VIP MENU
                            
                            dialogStageTwo.setScene(new Scene(vboxTwo));
                            dialogStageTwo.show();
            				stag4.close();
            			}
            			
            			
            		});
                });
                
                connectToVIPTab.setOnAction(x -> {
                	ConnectLabel = new Label("Connect to a VIP:");
                	ConnectListView = new ListView<String>();
                	  
                	for(int p = 0; p < playerListVIP.size(); p++) {
                		  //SoccerPlayer empty = new SoccerPlayer("");
                  		  //playerList.add(empty);
                		  ConnectListView.getItems().addAll(playerListVIP.get(p).getName());
                		  //playerList.remove(empty);
                	}
                	  
                	topBoxConnect = new HBox(60, ConnectLabel, ConnectListView);
                	topBoxConnect.setPadding(new Insets(10, 20, 10, 20));
                	topBoxConnect.setAlignment(Pos.CENTER);
                	  
                	  //CHANGE METHOD FOR ACTION
                	  
                	submitButtonUpdate = new Button("SELECT");
                	submitButtonUpdate.setOnAction(z -> connectToOtherUser());
                	  
                	sceneBoxUpdate = new VBox(topBoxConnect, submitButtonUpdate);
                	sceneBoxUpdate.setAlignment(Pos.CENTER);
                	  
                	Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
                	updateStage.setScene(sceneUpdate);
                	updateStage.show();
                });
                
                disconnectVIPTab.setOnAction(b -> {
                	ConnectLabel = new Label("Connect to a VIP:");
              	  	ConnectListView = new ListView<String>();
//              	
              	  for(int p = 0; p < connectedVIPS.size(); p++) {
            		  //SoccerPlayer empty = new SoccerPlayer("");
              		  //playerList.add(empty);
            		  ConnectListView.getItems().addAll(connectedVIPS.get(p).getName());
            		  //playerList.remove(empty);
              	  }
              	  
	              	  topBoxConnect = new HBox(60, ConnectLabel, ConnectListView);
	              	  topBoxConnect.setPadding(new Insets(10, 20, 10, 20));
	              	  topBoxConnect.setAlignment(Pos.CENTER);
	              	  
	              	  //CHANGE METHOD FOR ACTION
	              	  
	              	  submitButtonUpdate = new Button("SELECT");
	              	  submitButtonUpdate.setOnAction(z -> disconnectFromOtherUser());
	              	  
	              	  sceneBoxUpdate = new VBox(topBoxConnect, submitButtonUpdate);
	              	  sceneBoxUpdate.setAlignment(Pos.CENTER);
	              	  
	              	  Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
	              	  updateStage.setScene(sceneUpdate);
	              	  updateStage.show();
                });
                
                connectionsShowTab.setOnAction(w -> {
                	
                	String totalConnections = "";
                	
                	for(int t = 0; t < connectedVIPS.size(); t++) {
                		totalConnections += connectedVIPS.get(t).getName() + "\n"; 
                		//textArea.setText(connectedVIPS.get(t).getName());
                	}
                	
                	textArea.setText(totalConnections);
                	
                });
                
                saveVIPConnectionList.setOnAction(f -> {
                	
                	try {
						createEmptyConnectedUsersFile();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                	
                	try {
						appendConnectedUsersFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                
                //ADDS TABS TO EACH MENU ITEM
                fileTab.getItems().addAll(
                    importPlayerListTab, importPrefencesTab, savetab, exitMenuTab
                ); 
                
                playerListTab.getItems().addAll(
                	createNewListTab, viewPlayerListTab, updatePlayerListTab, removePlayersTab
                );
                
                goalListTab.getItems().addAll(
                   viewGoalsTab, addGoalsTab  
                );
                
                preferencesTab.getItems().addAll(
                	viewPreferencesTab, changeGoalSortTab, changeNameSortTab, changeSummaryTab, updatePasswordTab
                );
                
                connectionsTab.getItems().addAll(
                		connectionsShowTab, connectToVIPTab, disconnectVIPTab, saveVIPConnectionList
                );
                
                
                // Add menus to menu bar
                mainMenu.getMenus().addAll(fileTab, playerListTab, goalListTab, preferencesTab, connectionsTab);

                // Add menu bar to Pane
                pane.getChildren().add(mainMenu);
                pane.getChildren().add(textArea);
                
            } else {
            	
            	//NON VIP - LESS OPTIONS TO DO
            	
            	Text textArea = new Text("");
            	
            	// Create Pane to host UI elements
                VBox pane = new VBox();
                
                // Create menu bar to host menu items
                MenuBar mainMenu = new MenuBar();

                // Create new menus to the bar
                Menu fileTab = new Menu("File");
                
                javafx.scene.control.MenuItem importPlayerListTab = new javafx.scene.control.MenuItem("Import Player List");
                javafx.scene.control.MenuItem importPrefencesTab = new javafx.scene.control.MenuItem("Import Preferences");
                javafx.scene.control.MenuItem savetab = new javafx.scene.control.MenuItem("Save All");
                javafx.scene.control.MenuItem exitMenuTab = new javafx.scene.control.MenuItem("Exit");
                
                Menu playerListTab = new Menu("Players");
                
                javafx.scene.control.MenuItem createNewListTab = new javafx.scene.control.MenuItem("Create New List");
                javafx.scene.control.MenuItem viewPlayerListTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem updatePlayerListTab = new javafx.scene.control.MenuItem("Update Players");
                javafx.scene.control.MenuItem removePlayersTab = new javafx.scene.control.MenuItem("Remove Players");
                
                Menu goalListTab = new Menu("Goal List");
                
                javafx.scene.control.MenuItem viewGoalsTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem addGoalsTab = new javafx.scene.control.MenuItem("Add Goals");
                
                Menu preferencesTab = new Menu("Prefences");
                
                javafx.scene.control.MenuItem viewPreferencesTab = new javafx.scene.control.MenuItem("View");
                javafx.scene.control.MenuItem changeGoalSortTab = new javafx.scene.control.MenuItem("Change Goal Sort");
                javafx.scene.control.MenuItem changeNameSortTab = new javafx.scene.control.MenuItem("Change Name Sort");
                javafx.scene.control.MenuItem changeSummaryTab = new javafx.scene.control.MenuItem("Change Summary Display");
                 
                Scene sceneMain = new Scene(pane, 500, 650);
                primaryStage.setScene(sceneMain);
                primaryStage.show();
                
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);

                VBox vbox = new VBox(new Text("Sent to the Regular Menu"));
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(15));

                dialogStage.setScene(new Scene(vbox));
                dialogStage.show();
                
                createNewListTab.setOnAction(r -> {
                	if(listCreated == false) {
                		Label playerAmountLabel = new Label("Please enter the number of players that would like to track 5 max");
                		playerAmountLabel.setMinWidth(100);
                		TextField playerAmountTextField = new TextField();
                		playerAmountTextField.setMinWidth(100);
//
                		Button btnCreateList = new Button();
//
                		// Set the text, X & Y coordinates
                		btnCreateList.setLayoutX(100);
                		btnCreateList.setLayoutY(80);
                		btnCreateList.setText("Enter");
//
                		VBox createPane = new VBox(10, playerAmountLabel, playerAmountTextField);
                		createPane.getChildren().add(btnCreateList);
                		createPane.setAlignment(Pos.CENTER);

                		Stage stag2 = new Stage();
                		
                		Scene sceneCreatePlayerAmount = new Scene(createPane, 300, 250);
                		stag2.setScene(sceneCreatePlayerAmount);
                		stag2.show();
                		
                		btnCreateList.setOnAction(a -> {
                			
                			amountOfPlayers = Integer.parseInt(playerAmountTextField.getText());
                			System.out.println(amountOfPlayers);
                			stag2.close();
                			
                			//CREATES EMPTY FILE TO BEGIN STORING DATA IF FILE DOES NOT EXIST
    						try {
    							createEmptyFile();
    						} catch (FileNotFoundException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    						
    						if (amountOfPlayers <=5 && amountOfPlayers >0) {
    							
    							//WARNING
    							System.out.println("**** CAUTION ****\nCreating Players with duplicate names will\ncause your list to be deleted.\n");
    							Stage dialogStageWarning = new Stage();
    			                //dialogStageWarning.initModality(Modality.WINDOW_MODAL);

    			                VBox vboxWarning = new VBox(new Text("**** CAUTION ****\nCreating Players with duplicate names will\ncause your list to be deleted.\n"));
    			                vboxWarning.setAlignment(Pos.CENTER);
    			                vboxWarning.setPadding(new Insets(15));

    			                dialogStageWarning.setScene(new Scene(vboxWarning));
    			                dialogStageWarning.show();
    							
    							//CREATES PLAYER OBJECTS
    							for (int i =0; i < amountOfPlayers; i++) {
    								Label playerNameLabel = new Label("Please enter the name of player ");
    		                		playerNameLabel.setMinWidth(100);
    		                		TextField playerNameTextField = new TextField();
    		                		playerNameTextField.setMinWidth(100);
    		//
    		                		Button btnPlayerName = new Button();
    		//
    		                		// Set the text, X & Y coordinates
    		                		btnPlayerName.setLayoutX(100);
    		                		btnPlayerName.setLayoutY(80);
    		                		btnPlayerName.setText("Enter");
    		//
    		                		VBox createPlayerNamePane = new VBox(10, playerNameLabel, playerNameTextField);
    		                		createPlayerNamePane.getChildren().add(btnPlayerName);

    		                		Scene sceneCreatePlayerName = new Scene(createPlayerNamePane, 300, 250);
    		                		Stage playerNameSet = new Stage();
    		                		playerNameSet.setScene(sceneCreatePlayerName);
    		                		playerNameSet.show();
    		                		
    		                		btnPlayerName.setOnAction(v -> {
    		                			
    		                			playerNameSet.close();
    		                			String playerName = playerNameTextField.getText();
    		                			System.out.println(playerName);
    		                			SoccerPlayer player = new SoccerPlayer(playerName);
    									playerList.add(player);
    		                			
    		                		});
    		                		
    							}
    							
    							//LIST CREATED
    							listCreated = true;
    							//CHECK DUPLICATE VALUES
    							checkDuplicate();
    							
    						} else {
    							//CATCH
    							textArea.setText("You must enter a number between 0 and 5");
    						}
                		});
					} else {
						textArea.setText("You must delete a list first");
					}
                });
                
                viewPlayerListTab.setOnAction(r -> {
                	
                	if(listCreated == true) {
                			textArea.setText("");
                			String a = "";
                			SoccerPlayer empty = new SoccerPlayer("");
                			playerList.add(empty);
                			for(int u =0; u < amountOfPlayers; u++) {
                				a = a + playerList.get(u).getName() + "\n";
                				//textArea.setText("\n " + playerList.get(u).getName());
                				//pane.getChildren().add(new Text(playerList.get(u).getName()));
                			}
                			textArea.setText(a);
                			playerList.remove(empty);
                	} else {
                		textArea.setText("You must create a list first");
                		
                	}
                });
                
                updatePlayerListTab.setOnAction(x -> {
                	if(listCreated == true) {  
                	  UpdateLabel = new Label("Choose a Player:");
                	  UpdateListView = new ListView<String>();
                	  for(int p = 0; p < amountOfPlayers; p++) {
                		  SoccerPlayer empty = new SoccerPlayer("");
                  		  playerList.add(empty);
                		  UpdateListView.getItems().addAll(playerList.get(p).getName());
                		  playerList.remove(empty);
                	  }
                	  
                	  topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
                	  topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
                	  topBoxUpdate.setAlignment(Pos.CENTER);
                	  
                	  submitButtonUpdate = new Button("SELECT");
                	  submitButtonUpdate.setOnAction(z -> updatePlayer());
                	  
                	  sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
                	  sceneBoxUpdate.setAlignment(Pos.CENTER);
                	  
                	  Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
                	  updateStage.setScene(sceneUpdate);
                	  updateStage.show();
                	} else {
                		textArea.setText("You must create a list first");
                	}
                });
                
                removePlayersTab.setOnAction(y -> {
                	if(listCreated == true) {
                	UpdateLabel = new Label("Choose a Player:");
                	UpdateListView = new ListView<String>();
              	  	for(int p = 0; p < amountOfPlayers; p++) {
              		  UpdateListView.getItems().addAll(playerList.get(p).getName());
              	  	}
              	  	
              	    UpdateListView.getItems().addAll("Clear all");
              	  
              	  	topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
              	  	topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
              	  	topBoxUpdate.setAlignment(Pos.CENTER);
              	  
              	  	submitButtonUpdate = new Button("SELECT");
              	  	submitButtonUpdate.setOnAction(z -> removePlayer());
              	  
              	  	sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
              	  	sceneBoxUpdate.setAlignment(Pos.CENTER);
              	  
              	  	Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
              	  	updateStage.setScene(sceneUpdate);
              	  	updateStage.show();
                	} else {
                		textArea.setText("You must create a list first");
                	}
                });
                
                viewGoalsTab.setOnAction(b -> {
                	if(listCreated == true) {
                		if(namesOrderAscending) {
							sortNames();
						} else {
							sortNamesDescending();
						}
						
						if(goalsSortDescending) {
							sortGoals();
						} else {
							sortGoalsAscending();
						}
						
						
            			textArea.setText("");
            			String a = "";
            			SoccerPlayer empty = new SoccerPlayer("");
            			playerList.add(empty);
            			for(int u =0; u < amountOfPlayers; u++) {
            				a = a + playerList.get(u).getName() + "       " + playerList.get(u).getgoals() + "\n";
            				//textArea.setText("\n " + playerList.get(u).getName());
            				//pane.getChildren().add(new Text(playerList.get(u).getName()));
            			}
            			
            			if(showSummary) {
							a = a + "\n" + showPlayerSummary();
						} else {
							//nothing shown
						}
            			
            			textArea.setText(a);
            			playerList.remove(empty);
            			goalstotal = 0;
            	} else {
            		textArea.setText("you must make a list first");
            		
            	}
                });
                
                addGoalsTab.setOnAction(x -> {
                	if(listCreated == true) {  
                  	  UpdateLabel = new Label("Choose a Player:");
                  	  UpdateListView = new ListView<String>();
                  	  for(int p = 0; p < amountOfPlayers; p++) {
                  		  SoccerPlayer empty = new SoccerPlayer("");
                    		  playerList.add(empty);
                  		  UpdateListView.getItems().addAll(playerList.get(p).getName());
                  		  playerList.remove(empty);
                  	  }
                  	  
                  	  topBoxUpdate = new HBox(60, UpdateLabel, UpdateListView);
                  	  topBoxUpdate.setPadding(new Insets(10, 20, 10, 20));
                  	  topBoxUpdate.setAlignment(Pos.CENTER);
                  	  
                  	  submitButtonUpdate = new Button("SELECT");
                  	  submitButtonUpdate.setOnAction(z -> updatePlayerGoals());
                  	  
                  	  sceneBoxUpdate = new VBox(topBoxUpdate, submitButtonUpdate);
                  	  sceneBoxUpdate.setAlignment(Pos.CENTER);
                  	  
                  	  Scene sceneUpdate = new Scene(sceneBoxUpdate, 500, 400);
                  	  updateStage.setScene(sceneUpdate);
                  	  updateStage.show();
                  	} else {
                  		pane.getChildren().add(new Text("You must make a list first"));
                  	}
                });
                
                viewPreferencesTab.setOnAction(d -> {
                	
            			textArea.setText("");
            			String a = "1. Goals sort order " + "[" + goalsSortStatus + "]"
							+ "\n2. Names sort order " + "[" + namesSortStatus + "]" + 
									"\n3. Show goal summary " + "[" + showSummary + "]"
									+ "\n4. Return to main menu\n";
            			
            			textArea.setText(a);
                });
                
                changeGoalSortTab.setOnAction(w -> {
                	textArea.setText("This feature is only allowed for VIP users, please\n contact Sassy Maven is you want to purchase the VIP verison\nof this program");
                });
                
                changeNameSortTab.setOnAction(c -> {
                	textArea.setText("This feature is only allowed for VIP users, please\n contact Sassy Maven is you want to purchase the VIP verison\nof this program");
                });
                
                changeSummaryTab.setOnAction(q -> {
                	textArea.setText("This feature is only allowed for VIP users, please\n contact Sassy Maven is you want to purchase the VIP verison\nof this program");
                });
                
                importPlayerListTab.setOnAction(i -> {
                	try {
						readFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                
                importPrefencesTab.setOnAction(s -> {
                	textArea.setText("This feature is only allowed for VIP users, please\n contact Sassy Maven is you want to purchase the VIP verison\nof this program");
                });
                
                savetab.setOnAction(l -> {
                	//CREATE EMPTY FILE TO STORE PLAYER AND GOAL VALUES
					try {
						createEmptyFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PLAYER NAMES AND GOALS
					try {
						appendFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					textArea.setText("Files saved. Thank you");
                });
                
                importPrefencesTab.setOnAction(r -> {
                	textArea.setText("This feature is only allowed for VIP users, please\n contact Sassy Maven is you want to purchase the VIP verison\nof this program");
                });
                
                exitMenuTab.setOnAction(f -> {
                	try {
						createEmptyFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//SAVE PLAYER NAMES AND GOALS
					try {
						appendFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
                	System.exit(0);
                });
                
                fileTab.getItems().addAll(
                    importPlayerListTab, importPrefencesTab, savetab, exitMenuTab
                ); 
                
                playerListTab.getItems().addAll(
                	createNewListTab, viewPlayerListTab, updatePlayerListTab, removePlayersTab
                );
                
                goalListTab.getItems().addAll(
                   viewGoalsTab, addGoalsTab  
                );
                
                preferencesTab.getItems().addAll(
                	viewPreferencesTab, changeGoalSortTab, changeNameSortTab, changeSummaryTab
                );
                
                
                // Add menus to menu bar
                mainMenu.getMenus().addAll(fileTab, playerListTab, goalListTab, preferencesTab);

                // Add menu bar to Pane
                pane.getChildren().add(mainMenu);
                pane.getChildren().add(textArea);
                
                
                
            }
            
            if(passwordEntry.equals("#ChelseaIsTheBest")) {
        		Label initialPasswordReset = new Label("Please enter a new password");
        		initialPasswordReset.setMinWidth(100);
        		TextField initialPasswordResetTextField = new TextField();
        		initialPasswordResetTextField.setMinWidth(100);
//
        		Button btnInitialPasswordReset = new Button();
//
        		// Set the text, X & Y coordinates
        		btnInitialPasswordReset.setLayoutX(100);
        		btnInitialPasswordReset.setLayoutY(80);
        		btnInitialPasswordReset.setText("Enter");
//
        		VBox initialPasswordResetPane = new VBox(10, initialPasswordReset, initialPasswordResetTextField);
        		initialPasswordResetPane.setAlignment(Pos.CENTER);
        		initialPasswordResetPane.getChildren().add(btnInitialPasswordReset);

        		Stage initialPasswordResetStage = new Stage();
        		
        		Scene sceneInitialPassword = new Scene(initialPasswordResetPane, 300, 250);
        		initialPasswordResetStage.setScene(sceneInitialPassword);
        		initialPasswordResetStage.show();
        		
        		btnInitialPasswordReset.setOnAction(y -> {
        			initialPasswordResetStage.close();
        			password = initialPasswordResetTextField.getText();
        			
        			Stage dialogStage5 = new Stage();
                    dialogStage5.initModality(Modality.WINDOW_MODAL);

                    VBox vboxReset = new VBox(new Text("Password Successfully Changed"));
                    vboxReset.setAlignment(Pos.CENTER);
                    vboxReset.setPadding(new Insets(15));
                    	
                    try {
						createBasicPasswordFile();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                    //SENT TO VIP MENU
                    
                    dialogStage5.setScene(new Scene(vboxReset));
                    dialogStage5.show();
        		});
        	} 
        });
    }

    public static void main(String[] args) {
    	try {
			readPasswordFile();
		} catch (FileNotFoundException e) {
			try {
				createBasicPasswordFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	
    	for(User u:SassyUserProvider.getInstance().getUsers())
        {
              
        	playerListVIP.add(u);
        		
        }
    	
    	try {
			readConnectedUsersFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Application.launch(args);
    }
    
    public static void updatePlayer() {
    	
    	String selectedPlayer = (UpdateListView.getSelectionModel().getSelectedItem() == null) ? "C#" : UpdateListView.getSelectionModel().getSelectedItem();
    	System.out.println("You've selected " + selectedPlayer);
    	
    	Label updatePlayerLabel = new Label("Please enter the new name for this Player");
		updatePlayerLabel.setMinWidth(100);
		TextField updatePlayerTextField = new TextField();
		updatePlayerTextField.setMinWidth(100);
//
		Button btnUpdatePlayer = new Button();
//
		// Set the text, X & Y coordinates
		btnUpdatePlayer.setLayoutX(100);
		btnUpdatePlayer.setLayoutY(80);
		btnUpdatePlayer.setText("Enter");
//
		VBox UpdatePlayerPane = new VBox(10, updatePlayerLabel, updatePlayerTextField);
		UpdatePlayerPane.getChildren().add(btnUpdatePlayer);

		Scene sceneCreatePlayerAmount = new Scene(UpdatePlayerPane, 300, 250);
		updateStage.setScene(sceneCreatePlayerAmount);
		updateStage.show();
		
		btnUpdatePlayer.setOnAction(w -> {
			updateStage.close();
			playerList.get(UpdateListView.getItems().indexOf(selectedPlayer)).setName(updatePlayerTextField.getText());
			playerList.get(UpdateListView.getItems().indexOf(selectedPlayer)).settGoals(0);
		});
		
    }
    
    public static void removePlayer() {
    	
    	updateStage.close();
    	if(UpdateListView.getSelectionModel().getSelectedItem() == "Clear all") {
    		playerList.clear();
			listCreated = false;
    	} else {
    		SoccerPlayer empty = new SoccerPlayer("");
    		playerList.add(empty);
	    	String selectedPlayer = (UpdateListView.getSelectionModel().getSelectedItem() == null) ? "C#" : UpdateListView.getSelectionModel().getSelectedItem();
	    	playerList.remove(UpdateListView.getItems().indexOf(selectedPlayer));
	    	playerList.remove(empty);
    	}
    	
    }
    
    public static void updatePlayerGoals() {
    	String selectedPlayer = (UpdateListView.getSelectionModel().getSelectedItem() == null) ? "C#" : UpdateListView.getSelectionModel().getSelectedItem();
    	System.out.println("You've selected " + selectedPlayer);
    	
    	Label updatePlayerLabel = new Label("Please enter the amount of goals you would like to add to this player");
		updatePlayerLabel.setMinWidth(100);
		TextField updatePlayerTextField = new TextField();
		updatePlayerTextField.setMinWidth(100);
//
		Button btnUpdatePlayer = new Button();
//
		// Set the text, X & Y coordinates
		btnUpdatePlayer.setLayoutX(100);
		btnUpdatePlayer.setLayoutY(80);
		btnUpdatePlayer.setText("Enter");
//
		VBox UpdatePlayerPane = new VBox(10, updatePlayerLabel, updatePlayerTextField);
		UpdatePlayerPane.getChildren().add(btnUpdatePlayer);

		Scene sceneCreatePlayerAmount = new Scene(UpdatePlayerPane, 300, 250);
		updateStage.setScene(sceneCreatePlayerAmount);
		updateStage.show();
		
		btnUpdatePlayer.setOnAction(w -> {
			updateStage.close();
			//playerList.get(UpdateListView.getItems().indexOf(selectedPlayer)).setName(updatePlayerTextField.getText());
			playerList.get(UpdateListView.getItems().indexOf(selectedPlayer)).setGoals(Integer.parseInt(updatePlayerTextField.getText()));
		});
    }
    
    public static void sortGoals() {
    	
		//SORT PLAYER GOALS BY DESCENDING VALUES
		Collections.sort(playerList, new Comparator<SoccerPlayer>(){
			
			//COMPARES NAMES
		  public int compare(SoccerPlayer o1, SoccerPlayer o2)
		  {
			  return o2.getgoals() - o1.getgoals();
			
		  }
		});
		
	}
	
	public static void sortNames() {
		
		//SORT NAMES ALPHABETICALLY
		Collections.sort(playerList, new Comparator<SoccerPlayer>() {
		    public int compare(SoccerPlayer v1,SoccerPlayer v2) {
		    	//RETURNS POSITIVE OR NEGATIVE VALUE
		        return v1.getName().compareTo(v2.getName());
		    }
		});
		
	}
	
	public static void sortNamesExternal(int userID) {
		
		//for(int s = 0; s < playerListVIP.get(userID - 1).getSassers().length; s++) {
		//SORT NAMES ALPHABETICALLY
		Arrays.sort(playerListVIP.get(userID - 1).getSassers(), new Comparator<Sasser>() {
		    public int compare(Sasser v1,Sasser v2) {
		    	//RETURNS POSITIVE OR NEGATIVE VALUE
		        return v2.getName().compareTo(v1.getName());
		    }
		});
		//}
		
	}
	
	public static void sortNamesDescendingExternal(int userID) {
		//for(int s = 0; s < playerListVIP.get(userID - 1).getSassers().length; s++) {
			//SORT NAMES ALPHABETICALLY
			Arrays.sort(playerListVIP.get(userID - 1).getSassers(), new Comparator<Sasser>() {
			    public int compare(Sasser v1,Sasser v2) {
			    	//RETURNS POSITIVE OR NEGATIVE VALUE
			        return v1.getName().compareTo(v2.getName());
			    }
			});
		//	}
	}
	
	public static void sortGoalsAscending() {
		//SORTS GOALS BY ASCENDING VALUE
		Collections.sort(playerList, new Comparator<SoccerPlayer>(){
			
			  public int compare(SoccerPlayer o1, SoccerPlayer o2)
			  {
				  //RETURNS POSITIVE OR NEGATIVE INT
				 return Integer.compare(o1.getgoals(), o2.getgoals());
			     
			  }
			});
	}
	
	public static void sortNamesDescending() {
		//SORTS NAMES IN DESCENDING VALUE BY REVERSING ALPHABETICALLY NAMES
		sortNames();
		Collections.reverse(playerList);
	}
	
	public static String showPlayerSummary() {
		
		//SHOWS PLAYERS GOAL SUMMARY
		
		for (int u = 0; u < playerList.size(); u++) {
			goalstotal += playerList.get(u).getgoals();
		}
		
		//SHOWS GOAL SUMMARY
		
		System.out.println("\n" + playerList.get(topPlayer()).getName() + " is the top scorer\nAverage number of goals is " + average(goalstotal, playerList.size()) + 
		"\nThe total number of goals is " + goalstotal);
		
		//SETS TOTAL GOALS TO ZERO TO PREVENT EXTRA ADDITION EACH TIME
		
		
		return "\n" + playerList.get(topPlayer()).getName() + " is the top scorer\nAverage number of goals is " + average(goalstotal, playerList.size()) + 
				"\nThe total number of goals is " + goalstotal;
		
	}
	
	public static String showPlayerSummaryExternal(int userID) {
				//SHOWS PLAYERS GOAL SUMMARY
		
					for(int e = 0; e < playerListVIP.get(userID - 1).getSassers().length; e++) {
						goalstotalExternal += playerListVIP.get(userID - 1).getSassers()[e].getGoals();
					}
				
				
				//SETS TOTAL GOALS TO ZERO TO PREVENT EXTRA ADDITION EACH TIME
				
				return "\n" + playerListVIP.get(userID - 1).getSassers()[topPlayerExternal(userID - 1)].getName() + " is the top scorer\nAverage number of goals is " + average(goalstotalExternal, playerListVIP.get(userID - 1).getSassers().length) + 
						"\nThe total number of goals is " + goalstotalExternal + "\n";
	}
	
	public static void sortGoalsExternal(int userID) {
		//for(int s = 0; s < playerListVIP.get(userID - 1).getSassers().length; s++) {
		Arrays.sort(playerListVIP.get(userID - 1).getSassers(), new Comparator<Sasser>(){
			
			//COMPARES NAMES
		 
		  public int compare(Sasser o1, Sasser o2)
		  {
			  return o2.getGoals() - o1.getGoals();
			
		  }
		
		});
		//}
	}
	
	public static void sortGoalsAscendingExternal(int userID) {
		//SORTS GOALS BY ASCENDING VALUE
		//for(int s = 0; s < playerListVIP.get(userID - 1).getSassers().length; s++) {
		Arrays.sort(playerListVIP.get(userID - 1).getSassers(), new Comparator<Sasser>(){
			
			  public int compare(Sasser o1, Sasser o2)
			  {
				  //RETURNS POSITIVE OR NEGATIVE INT
				 return Integer.compare(o1.getGoals(), o2.getGoals());
			     
			  }
			});
		//}
	}
	
	public static double average(int a, int b) {
		//GETS AVERAGE OF ALL GOALS
		double avg = 0;
		
		avg = (double) a / b;
		return avg;
		
	}
	
	public static int topPlayer() {
		//DETERMIENS TOP PLAYER WITH MOST GOALS
		int top = 0;
		
		if (goalsSortDescending == false) {
			top = (amountOfPlayers - 1);
		} else {
			top = 0;
		}
		return top;
	}
	
	public static int topPlayerExternal(int userID) {
		int top = 0;
		
		if (goalsSortDescending == false) {
			top = (playerListVIP.get(userID).getSassers().length - 1);
		} else {
			top = 0;
		}
		return top;
	}
	
	public static void checkDuplicate() {
		
		//CREATE A NEW HASHSET
		Set<String> uniquUsers = new HashSet<String>();

        for (int g = 0; g < playerList.size(); g++) {
        	//ADDS A PLAYER TO THE SET IF THE NAME IS DUPLICATED IN THE ARRAY
            if (!uniquUsers.add(playerList.get(g).getName())) {
            	
            	//SETS THE NAME TO DUPLICATE, DOESN'T AFFECT PROGRAM OUTPUT
                //playerList.get(g).getName() = "Duplicate"; 
                System.out.println("Sorry no duplicate names. Your list will be deleted.\n");
                playerList.clear();
                listCreated = false;
                //SETS UP FOR A NEW ARRAY TO BE CREATED
            	//playerListStatus = false;
            	
            }
        }
		
	}
	
	public static void readFile() throws FileNotFoundException {
		
		Alert confirm = 
				new Alert(AlertType.CONFIRMATION, 
				"Are you sure you do that?!!!");

			Optional<ButtonType> response = confirm.showAndWait();

			if(response.isPresent() && response.get() == ButtonType.CANCEL) {
				System.out.println("You cancelled.");
				
			} else {
				
				
				Label playerAmountLabel = new Label("Please enter the number of players that would like to track");
	    		playerAmountLabel.setMinWidth(100);
	    		TextField playerAmountTextField = new TextField();
	    		playerAmountTextField.setMinWidth(100);
	//
	    		Button btnCreateList = new Button();
	//
	    		// Set the text, X & Y coordinates
	    		btnCreateList.setLayoutX(100);
	    		btnCreateList.setLayoutY(80);
	    		btnCreateList.setText("Enter");
	//
	    		VBox createPane = new VBox(10, playerAmountLabel, playerAmountTextField);
	    		createPane.getChildren().add(btnCreateList);

	    		Stage stag2 = new Stage();
	    		
	    		btnCreateList.setOnAction(t -> {
	    			stag2.close();
	    			
		        	File myFile = new File("SoccerPlayerData.txt");
					Scanner inputReader = null;
					try {
						inputReader = new Scanner(myFile);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	    			amountOfPlayers = Integer.parseInt(playerAmountTextField.getText());
					
					for(int p = 0; p < amountOfPlayers; p++) {
						SoccerPlayer player = new SoccerPlayer("");
						playerList.add(player);
					}
					
					for(int f = 0; f < amountOfPlayers; f++) {
						
							//READS NAME LINE
							String nameLine = inputReader.nextLine();
							
							//SETS NAME VALUE
							playerList.get((f)).setName(nameLine);
							
							//READS INT LINE
							String goalsLine = inputReader.nextLine();
							
							//SETS GOAL VALUE
							playerList.get((f)).settGoals(Integer.parseInt(goalsLine));
							
							//FILE CHECK
							if(f == 0 && nameLine.length() == 0) {
								f = 8;
								System.out.println("A file with saved preferences does not yet exist");
								listCreated = false;
							}
							
							//DETERMIENS THAT THE LIST IS TRUE
							listCreated = true;
							
					}
					inputReader.close();
	    			
	    		});
	    		Scene sceneCreatePlayerAmount = new Scene(createPane, 300, 250);
	    		stag2.setScene(sceneCreatePlayerAmount);
	    		stag2.show();
	    		
	    		
	        	
			}
		
	}
	
	public static void appendFile() throws IOException {
		
		//STORES DATA TO THE FILE
			FileWriter fwriter = new FileWriter("SoccerPlayerData.txt");
			PrintWriter outputWriter = new PrintWriter(fwriter);
			
			//STORES DATA TO FILE
			for(int f = 0; f < (playerList.size()); f++) {
				outputWriter.println(playerList.get(f).getName() /*+ " " + playerList.get(f).getgoals()*/);
				outputWriter.println(playerList.get(f).getgoals());
			}
			
			//CLOSE IT FOR GOOD MEASURES
			outputWriter.close();
	
	}
	
	public static void createEmptyFile() throws FileNotFoundException {
		
		//CREATES EMPTY TEXT FILE FOR PLAYER DATA STORAGE
		PrintWriter outputWriter = new PrintWriter("SoccerPlayerData.txt");
	}
	
	public static void createBasicPasswordFile() throws FileNotFoundException {
		
		//CREATES EMPTY TEXT FILE FOR PLAYER DATA STORAGE
		PrintWriter outputWriter = new PrintWriter("SassySoccerAppPassword.txt");
		try {
			FileWriter fwriter = new FileWriter("SassySoccerAppPassword.txt");
			PrintWriter outputWriterPassword = new PrintWriter(fwriter);
			outputWriterPassword.println(password);
			
			outputWriterPassword.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void appendPasswordFile() throws IOException {
		
		//APPENDS PREFERENCE FILES
		FileWriter fwriter = new FileWriter("SassySoccerAppPassword.txt");
		PrintWriter outputWriterPassword = new PrintWriter(fwriter);
		
		outputWriterPassword.println(password);
		
		outputWriterPassword.close();
	}
	
	public static void readPasswordFile() throws FileNotFoundException {
		File myFile = new File("SassySoccerAppPassword.txt");
		Scanner inputReaders = new Scanner(myFile);
		
		String passwordFromFile = inputReaders.nextLine();
		password = passwordFromFile;
	}
	
	public static void readPreferenceFile() throws FileNotFoundException {
		
		Alert confirm = 
				new Alert(AlertType.CONFIRMATION, 
				"Are you sure you do that?!!!");

			Optional<ButtonType> response = confirm.showAndWait();

			if(response.isPresent() && response.get() == ButtonType.CANCEL) {
				System.out.println("You cancelled.");
				System.out.println("\nRedirecting you to preferences menu\n");
				firstTime = false;
			}
			else {
				//CALL FILE TO GRAB FROM
				File myFile = new File("Preferences.txt");
				Scanner inputReaders = new Scanner(myFile);
				
				//INPUTS PREFERENCES
				String goalsSortDescendingStr = inputReaders.nextLine();
				String namesOrderAscendingStr = inputReaders.nextLine();
				String showSummaryStr = inputReaders.nextLine();
				
				//APPLYS PREFERENCES VALUES
				if(goalsSortDescendingStr.equals("true")) {
					goalsSortDescending = true;
					goalsSortStatus = "Descending";
				} else {
					goalsSortDescending = false;
					goalsSortStatus = "Ascending";
				}
				
				if(namesOrderAscendingStr.equals("true")) {
					namesOrderAscending = true;
					namesSortStatus = "Ascending";
				} else {
					namesOrderAscending = false;
					namesSortStatus = "Descending";
				}
				
				if (showSummaryStr.equals("true")) {
					showSummary = true;
				} else {
					showSummary = false;
				}
				
				//CALLS FIRST TIME RUNNING PROGRAM TO FALSE
				firstTime = false;
				System.out.print("Now you've done it!!!");
			}
		
	}
	
	public static void createEmptyPreferenceFile() throws FileNotFoundException {
		//CREATES EMPTY PREFERENCE FILE
		PrintWriter outputWriterPreference = new PrintWriter("Preferences.txt");
	}
	
	public static void appendPreferenceFile() throws IOException {
		
		//APPENDS PREFERENCE FILES
		FileWriter fwriter = new FileWriter("Preferences.txt");
		PrintWriter outputWriterPreference = new PrintWriter(fwriter);
		
		outputWriterPreference.println(goalsSortDescending);
		outputWriterPreference.println(namesOrderAscending);
		outputWriterPreference.println(showSummary);
		
		outputWriterPreference.close();
	}
	
	public static void connectToOtherUser() {
		
		String selectedUser = (ConnectListView.getSelectionModel().getSelectedItem() == null) ? "C#" : ConnectListView.getSelectionModel().getSelectedItem();
		
		for(int u = 0; u < connectedVIPS.size(); u++){
			if(selectedUser.equals(connectedVIPS.get(u).getName())){
				textArea.setText("You are already connected to this VIP User");
				u = connectedVIPS.size();
				connect = false;
			} 
			
		}
		
		if(connect == true) {
			//textArea.setText(playerListVIP.get(ConnectListView.getItems().indexOf(selectedUser)).getName());
			connectedVIPS.add(playerListVIP.get(ConnectListView.getItems().indexOf(selectedUser)));
		}
		
		textArea.setText("");
		connect = true;
		updateStage.close();
	}
	
	public static void disconnectFromOtherUser() {
		
		String selectedUser = (ConnectListView.getSelectionModel().getSelectedItem() == null) ? "C#" : ConnectListView.getSelectionModel().getSelectedItem();
		connectedVIPS.remove((ConnectListView.getItems().indexOf(selectedUser)));
		updateStage.close();
	}
	
	public static void createEmptyConnectedUsersFile() throws FileNotFoundException {
		//CREATES EMPTY PREFERENCE FILE
		PrintWriter outputWriterPreference = new PrintWriter("ConnectedUsers.txt");
	}
	
	public static void appendConnectedUsersFile() throws IOException {
		
		//APPENDS PREFERENCE FILES
		FileWriter fwriter = new FileWriter("ConnectedUsers.txt");
		PrintWriter outputWriterPreference = new PrintWriter(fwriter);
		
		for(int o = 0; o < connectedVIPS.size(); o++) {
			outputWriterPreference.println(connectedVIPS.get(o).getUserID());
		}
		
		outputWriterPreference.close();
	}
	
	public static void readConnectedUsersFile() throws FileNotFoundException {
		//connectedVIPS.clear();
		File myFile = new File("ConnectedUsers.txt");
		Scanner inputReaders = new Scanner(myFile);
		
		while(inputReaders.hasNextLine())
			connectedVIPS.add(playerListVIP.get((Integer.parseInt(inputReaders.nextLine()) - 1)));
		
			
		inputReaders.close();
	}

}
