package br.com.mariapuri.mydom.config.system;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
//@Component
//@ConfigurationProperties("mydom")
//public class PropertyConfig {
//  
//  private String origenPermitida = "http://ms-264:4200";
//  
//  @Value("${spring.mail.username}")
//  private String mailFrom;
//  
//  @Value("${nome.empresa}")
//  private String nomeEmpresa;
//
//  @Value("${url.empresa}")
//  private String urlEmpresa;
//  
//  @Value("${app.origem}")
//  private String server;  
//  
//  @Value("${integracao.sigt.url}")
//  private String urlSigt;  
//  
//  @Value("${info.app.release}")
//  private String versao;  
//
//  @Value("${info.app.release-data}")
//  private String dataVersao;
//  
//  private final Mail mail = new Mail();
//  private final Seguranca seguranca = new Seguranca();
//  
//  @Getter
//  @Setter
//  public static class Seguranca{
//    private boolean enableHttps;
//  }
//  
//  @Getter
//  @Setter
//  public static class Mail {
//    private String host;
//    private Integer port;
//    private String username;
//    private String password;
//    
//  }
//  
//}
