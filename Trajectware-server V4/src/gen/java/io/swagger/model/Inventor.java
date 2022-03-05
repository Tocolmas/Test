/*
 * Timeline
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * Inventor
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")public class Inventor   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("firstname")
  private String firstname = null;

  @JsonProperty("photoUrls")
  private List<File> photoUrls = new ArrayList<File>();

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("birthdate")
  private String birthdate = null;

  @JsonProperty("deathdate")
  private String deathdate = null;

  @JsonProperty("nationalite")
  private String nationalite = null;

  @JsonProperty("file")
  private String file = null;

  public Inventor id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Inventor name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @JsonProperty("name")
  @ApiModelProperty(example = "Thomas Edison", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Inventor firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
   **/
  @JsonProperty("firstname")
  @ApiModelProperty(value = "")
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public Inventor photoUrls(List<File> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public Inventor addPhotoUrlsItem(File photoUrlsItem) {
    this.photoUrls.add(photoUrlsItem);
    return this;
  }

  /**
   * Get photoUrls
   * @return photoUrls
   **/
  @JsonProperty("photoUrls")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<File> getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(List<File> photoUrls) {
    this.photoUrls = photoUrls;
  }

  public Inventor status(String status) {
    this.status = status;
    return this;
  }

  /**
   * inventors status in the dataBase
   * @return status
   **/
  @JsonProperty("status")
  @ApiModelProperty(value = "inventors status in the dataBase")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Inventor birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * when the inventor is born
   * @return birthdate
   **/
  @JsonProperty("birthdate")
  @ApiModelProperty(value = "when the inventor is born")
  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public Inventor deathdate(String deathdate) {
    this.deathdate = deathdate;
    return this;
  }

  /**
   * when the inventor is dead
   * @return deathdate
   **/
  @JsonProperty("deathdate")
  @ApiModelProperty(value = "when the inventor is dead")
  public String getDeathdate() {
    return deathdate;
  }

  public void setDeathdate(String deathdate) {
    this.deathdate = deathdate;
  }

  public Inventor nationalite(String nationalite) {
    this.nationalite = nationalite;
    return this;
  }

  /**
   * the nationality of the inventor
   * @return nationalite
   **/
  @JsonProperty("nationalite")
  @ApiModelProperty(value = "the nationality of the inventor")
  public String getNationalite() {
    return nationalite;
  }

  public void setNationalite(String nationalite) {
    this.nationalite = nationalite;
  }

  public Inventor file(String file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
   **/
  @JsonProperty("file")
  @ApiModelProperty(value = "")
  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Inventor inventor = (Inventor) o;
    return Objects.equals(this.id, inventor.id) &&
        Objects.equals(this.name, inventor.name) &&
        Objects.equals(this.firstname, inventor.firstname) &&
        Objects.equals(this.photoUrls, inventor.photoUrls) &&
        Objects.equals(this.status, inventor.status) &&
        Objects.equals(this.birthdate, inventor.birthdate) &&
        Objects.equals(this.deathdate, inventor.deathdate) &&
        Objects.equals(this.nationalite, inventor.nationalite) &&
        Objects.equals(this.file, inventor.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, firstname, photoUrls, status, birthdate, deathdate, nationalite, file);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Inventor {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    deathdate: ").append(toIndentedString(deathdate)).append("\n");
    sb.append("    nationalite: ").append(toIndentedString(nationalite)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
