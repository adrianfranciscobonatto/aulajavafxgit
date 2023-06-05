package br.com.senac.aula.javafx.controller;

import br.com.senac.aula.javafx.model.Cliente;
import br.com.senac.aula.javafx.model.Endereco;
import br.com.senac.aula.javafx.services.ClienteService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/mainapp.fxml")
public class ClienteController {

    @FXML
    private TextField nome;
    @FXML
    private TextField documento;
    @FXML
    private TextField rg;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone;

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, Integer> colunaID;
    @FXML
    private TableColumn<Cliente, Double> colunaDocumento;
    @FXML
    private TableColumn<Cliente, Double> colunaRG;
    @FXML
    private TableColumn<Cliente, String> colunaEmail;
    @FXML
    private TableColumn<Cliente, Double> colunaTelefone;
// Começo do Cadastro de endereços

    @FXML
    private TableView<Endereco> tabelaEndereco;
    @FXML
    private TextField incodigoCli;
    @FXML
    private TextField inCep;
    @FXML
    private TextField inBairro;
    @FXML
    private TextField inNumero;
    @FXML
    private TextField inCidade;
    @FXML
    private TextField inEstado;
    @FXML
    private TableColumn<Endereco, Integer> colunaId;
    @FXML
    private TableColumn<Endereco, Double> colunaCEP;
    @FXML
    private TableColumn<Endereco, String> colunaBairro;
    @FXML
    private TableColumn<Endereco, Integer> colunaNumero;
    @FXML
    private TableColumn<Endereco, String> colunaCidade;
    @FXML
    private TableColumn<Endereco, String> colunaEstado;
    private int index = -1;


    @FXML
    public void initialize() {

        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colunaRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        colunaId.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        colunaCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        this.carregarListaClientes();
        this.carregarListaEndereco();

        tabelaClientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Cliente cli = tabelaClientes.getSelectionModel().getSelectedItem();

                    nome.setText(cli.getNome());
                    documento.setText(String.valueOf(cli.getDocumento()));
                    rg.setText(String.valueOf(cli.getRg()));
                    email.setText(cli.getEmail());
                    telefone.setText(String.valueOf(cli.getTelefone()));

                    index = cli.getId();
                }
            }
        });

                    tabelaEndereco.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent evt) {
                            if (evt.getClickCount() == 2) {
                                Endereco end = tabelaEndereco.getSelectionModel().getSelectedItem();
                                incodigoCli.setText(String.valueOf(end.getId_cliente()));
                                inCep.setText(String.valueOf(end.getCep()));
                                inBairro.setText(end.getBairro());
                                inCidade.setText(end.getCidade());
                                inEstado.setText(end.getEstado());
                                inNumero.setText(String.valueOf(end.getNumero()));

                    index = end.getId_cliente();
                }
            }
        });
    }

    public void executarOkEnd(){
        Endereco end = new Endereco();
        end.setId_cliente(Integer.valueOf(incodigoCli.getText()));
        end.setCep(Double.valueOf((inCep.getText())));
        end.setBairro(inBairro.getText());
        end.setNumero(Integer.valueOf(inNumero.getText()));
        end.setCidade(inCidade.getText());
        end.setEstado(inEstado.getText());

            tabelaEndereco.getItems().addAll(end);
            ClienteService.inserirEndereco(end);
            index = -1;

            this.carregarListaEndereco();
            this.limparCamposEndereco();
    }

    public void executarOk() {

        Cliente cli = new Cliente();
        cli.setNome(nome.getText());
        cli.setDocumento(documento.getText());
        cli.setEmail(email.getText());
        cli.setRg(Double.valueOf((rg.getText())));
        cli.setTelefone(Double.valueOf((telefone.getText())));

        if (ClienteService.buscarClienteByDocumento(String.valueOf(cli.getDocumento()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta");
            alert.setHeaderText("Documento: " + String.valueOf(documento.getText()) + " já existe na base!");
            alert.show();
        } else if (!String.valueOf(cli.getDocumento()).matches("[0-9]*")){Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Documento invalido");
            alert.show();
} else {

            tabelaClientes.getItems().addAll(cli);
            ClienteService.inserirCliente(cli);
            index = -1;
            this.carregarListaClientes();
            this.limparCampos();
        }
        }

    public void executarExcluirCadastro(){
        if(index > -1){
            ClienteService.deletarEndereco(index);
            this.carregarListaEndereco();
            index = -1;
            this.limparCamposEndereco();
        }
    }
    public void executExcluir(){
        if(index > -1){
            ClienteService.deletarCliente(index);
            this.carregarListaClientes();
            index = -1;
            this.limparCampos();
        }
    }

    public void carregarListaClientes(){

        tabelaClientes.getItems().remove(0, tabelaClientes.getItems().size());

        List<Cliente> cliList = ClienteService.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);
    }

    public void carregarListaEndereco(){
        tabelaEndereco.getItems().remove(0, tabelaEndereco.getItems().size());

        List<Endereco> endList = ClienteService.carregarEndereco();

        tabelaEndereco.getItems().addAll(endList);

    }
    public void limparCampos(){
        nome.setText("");
        documento.setText("");
        rg.setText(String.valueOf(""));
        telefone.setText(String.valueOf(""));
        email.setText("");
    }

    public void limparCamposEndereco(){
        inCep.setText("");
        incodigoCli.setText(String.valueOf(("")));
        inCep.setText(String.valueOf(""));
        inNumero.setText(String.valueOf(""));
        inEstado.setText("");
        inBairro.setText("");
    }

}

