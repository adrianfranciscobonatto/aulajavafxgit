package br.com.senac.aula.javafx.services;

import br.com.senac.aula.javafx.db.ConexaoDataBase;
import br.com.senac.aula.javafx.model.Cliente;
import br.com.senac.aula.javafx.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static ConexaoDataBase conexao = new ConexaoDataBase();

    public static List<Endereco> carregarEndereco() {
        List<Endereco> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();

            Statement stas = conn.createStatement();
            ResultSet rse = stas.executeQuery("select * from endereco;");

            while (rse.next()){
                Endereco end = new Endereco(rse.getInt("id_cliente"),
                        rse.getDouble("cep"),
                        rse.getString("bairro"),
                        rse.getInt("numero"),
                        rse.getString("cidade"),
                        rse.getString("estado"));

            out.add(end);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }
    public static List<Cliente> carregarClientes(){
        List<Cliente> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from clientes;");

            while (rs.next()){
                Cliente cli = new Cliente(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("documento"),
                        rs.getDouble("rg"),
                        rs.getString("email"),
                        rs.getDouble("Telefone"));

                out.add(cli);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return out;
    }

    public static void inserirEndereco(Endereco endereco){
        try {
            Connection conn = conexao.getConexao();

            String sqlInsert = "insert into endereco (id_cliente, cep, bairro, numero, cidade, estado) values (?, ?, ?, ?, ?, ?)";

            PreparedStatement pres = conn.prepareStatement(sqlInsert);

            pres.setInt(1, endereco.getId_cliente());
            pres.setDouble(2, endereco.getCep());
            pres.setString(3, endereco.getBairro());
            pres.setInt(4, endereco.getNumero());
            pres.setString( 5,endereco.getCidade());
            pres.setString(6, endereco.getEstado());

            pres.execute();

            pres.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void inserirCliente(Cliente cliente){
        try {
            Connection conn = conexao.getConexao();

            String sqlInsert = "insert into clientes (nome, documento, email, rg, telefone) values (?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setString(1, cliente.getNome());
            pre.setString(2, cliente.getDocumento());
            pre.setString(3, cliente.getEmail());
            pre.setDouble( 4, cliente.getRg());
            pre.setDouble(5, cliente.getTelefone());

            pre.execute();

            pre.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletarCliente(int idCliente){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from clientes where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idCliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static boolean deletarEndereco(int id_cliente){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from endereco where id_cliente = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, id_cliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean buscarClienteByDocumento(String documento){
        try{
            Connection conn = conexao.getConexao();

            String selectSql = "select id from clientes where documento = '" + documento + "'";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
