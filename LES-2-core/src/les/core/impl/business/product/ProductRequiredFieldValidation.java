package les.core.impl.business.product;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.product.Phone;

public class ProductRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;
			
			String model = phone.getModel();
			Integer expandability = phone.getExpandability();
			Double screenSize = phone.getScreenSize();
			Double screenResol = phone.getScreenResol();
			Double rCameraResol = phone.getRcameraResol();
			Double fCameraResol = phone.getFcameraResol();
			Double camcorderResol  = phone.getCamcorderResol();
			Double height = phone.getHeight();
			Double width = phone.getWidth();
			Double depth = phone.getDepth();
			Double weight = phone.getWeight();
			Integer ram = phone.getRamMemory();
			String packageContent = phone.getPackageContent();

			StringBuilder msg = new StringBuilder();
			if(phone.getActivationCategory() == null) {
				if(model.equals("") ) 
					msg.append("O nome do modelo deve ser preenchido!");
				
				if(packageContent.equals("") ) 
					msg.append("O conteúdo do pacote deve ser preenchido!");
					
				if(expandability == null ) 
					msg.append("A capacidade de expansão deve ser preenchida!");
				
				if(screenSize == null) 
					msg.append("O tamanho da tela deve ser preenchido!");
				
				if(screenSize < 0) 
					msg.append("O tamanho da tela é inválido!");
				
				if(screenResol == null) 
					msg.append("A resolução da tela deve ser preenchida!");
				
				if(screenResol < 0) 
					msg.append("A resolução da tela é inválida!");
				
				if(rCameraResol == null) 
					msg.append("A resolução da câmera traseira deve ser preenchida!");
				 
				if(rCameraResol < 0) 
					msg.append("A resolução da câmera traseira é inválida!");
				 
				if(fCameraResol == null) 
					msg.append("A resolução da câmera frontal deve ser preenchida!");
				
				if(fCameraResol < 0) 
					msg.append("A resolução da câmera frontal é inválida!");
				
				if(camcorderResol == null) 
					msg.append("A resolução da filmadora deve ser preenchida!");
				
				if(camcorderResol < 0) 
					msg.append("A resolução da filmadora é inválida!");
				
				if(height == null) 
					msg.append("A altura deve ser preenchida!");
				
				if(height < 0) 
					msg.append("A altura é inválida!");
				
				if(width == null) 
					msg.append("A largura deve ser preenchida!");
				
				if(width < 0) 
					msg.append("A largura é inválida!");
				
				if(depth == null) 
					msg.append("A profundidade deve ser preenchida!");
				
				if(depth < 0) 
					msg.append("A profundidade é inválida!");
				
				if(weight == null) 
					msg.append("O peso deve ser preenchido!");
				
				if(weight < 0) 
					msg.append("O peso é inválido!");
				
				if(ram != null && ram < 0) 
					msg.append("O memória RAM é inválida!");
				
				if(msg.length() > 0)
					return msg.toString();
			}
		}		
		return null;
	}
}
