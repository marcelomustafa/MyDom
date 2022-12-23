package br.com.mariapuri.mydom.component.tools;

import java.text.Normalizer;

import org.springframework.stereotype.Component;

@Component
public class ToolUtilsComponent {
	
	public String accentLass(String text) {
		String normalize = Normalizer.normalize(text,Normalizer.Form.NFD);
		return normalize.replaceAll("\\p{InCombiningDiacritiacalMarks}+", "");
	}

}
