package io.swagger.api.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import io.swagger.api.*;
import io.swagger.model.*;

import java.io.File;
import io.swagger.model.Inventor;
import io.swagger.model.ModelApiResponse;
import src.InsertInv;
import src.String;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2021-12-23T10:17:36.264Z")
public class InventorApiServiceImpl extends InventorApiService {
    @Override
    private Connection connect() {
	    String url = "jdbc:sqlite:c://sqlite/db/projet.db";
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
	public void insert(String NomActor, String DateNaissance, String DateMort, String Nationalite, String PrenomActor) {
	    String query = "INSERT INTO Actor(NomActor, DateNaissance, DateMort, Nationalite, PrenomActor) VALUES(?,?,?,?,?)";
	
	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, NomActor);
	        pstmt.setString(2, DateNaissance);
	        pstmt.setString(2, DateMort);
	        pstmt.setString(2, Nationalite);
	        pstmt.setString(2, PrenomActor);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public Response addInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
	    insert inv = new insert ();
	    // insert three new rows
	    inv.insert("Johns", "1935", "1985", "Americain", "Paul");
	    inv.insert("Sinclair", "1940", "2021", "Anglais", "Clive");
	    inv.insert("Jobs", "1955", "2011", "Americain", "Steve");	
	    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor added")).build();
}
    @Override
    /*delete*/
    public Response deleteInventor(Long inventorId, String apiKey, SecurityContext securityContext) throws NotFoundException {
      String query = "delete from Actor where Entity id = ?";
      
      try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, "Sinclair");
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor deleted!")).build();
	}
    
    @Override
    /*find inventor by invention*/
    
    public Response findInventorByInvention( @NotNull List<String> invention, SecurityContext securityContext) throws NotFoundException {
		String query = "select Entity id from Actor, Invention WHERE Entity id.Actor = Entity id.Invention "; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getInt(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}
  
    @Override
    public Response findInventorsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
    	String query = "select NomActor from Actor, Status WHERE NomActor.Actor = NomActor.Status "; /*Pas sur de pouvoir faire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getInt(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}

    @Override
    public Response findInventorsByTags( @NotNull List<String> tags,  String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Actor, Feature WHERE Entity id.Actor = Entity id.Feature"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getInt(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}

    @Override
    public Response getInventorById(Long inventorId, SecurityContext securityContext) throws NotFoundException {
    	String query = "select Entity id from Actor"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getInt(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}
    
    @Override
    public Response inventorfindByDate( String date, SecurityContext securityContext) throws NotFoundException {
    	String query = "select DateNaissance, DateMort from Actor"; /*Pas sur de pouvoir daire ça*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
		ResultSet rst = preparedStmt.executeQuery();
		System.out.println("tNomActor\t\tDateNaissance\t\tDateMort\t\tEntity id\t\tNationalite\t\tPrenomActor\n");
		while(rst.next()) {
		   System.out.print(rst.getString(1));
		   System.out.print("\t\t\t\t\t"+rst.getString(2));
		   System.out.print("\t\t\t\t\t"+rst.getString(3));
		   System.out.print("\t\t\t\t\t"+rst.getInt(4));
		   System.out.print("\t\t\t\t\t"+rst.getString(5));
		   System.out.print("\t\t\t\t\t"+rst.getString(6));
		   System.out.println();
		}}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor found!")).build();
	}
    @Override
    public Response updateInventor(Inventor inventor, SecurityContext securityContext) throws NotFoundException {
    	String query = "update Actor set DateMort = ? where NomActor = ? and PrenomActor = ?"; /*dans le cas ou l'inventeur n'est pas encore mort*/
		
		try (Connection conn = this.connect();
				PreparedStatement preparedStmt = conn.prepareStatement(query)){
	    preparedStmt.setString(1, "2022");
	    preparedStmt.setString(2, "Joe");
	    preparedStmt.setString(3, "John");
	    preparedStmt.executeUpdate();
		}catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Inventor updated!")).build();
	}
    
    @Override
    public Response updateInventorWithForm(Long inventorId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic! ??
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  
    }
    @Override
    public Response uploadImage(Long inventorId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
    	/*private static void insert(){
            try{
                FileInputStream fis;
                int num_rows = 0;
                File image = new File("C://Users//Steven//Desktop//Project Files//Java//SQLite//aa.jpg");
                fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;){
                    bos.write(buf, 0, readNum);
                }
                fis.close();
                Connection conn = connection("test");
                PreparedStatement ps = conn.prepareStatement("INSERT INTO image (id, photo) VALUES(1,?)");
                ps.setBytes(1, bos.toByteArray());
                
                num_rows = ps.executeUpdate();
                if(num_rows>0){
                    System.out.println("Data has been inserted");
                }
                ps.close();
                conn.close();*/ /*viens d'internet*/
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
