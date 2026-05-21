package br.com.rickicollab.ediaristas.core.Listeners;

import br.com.rickicollab.ediaristas.core.config.SpringContext;
import br.com.rickicollab.ediaristas.core.models.Foto;
import br.com.rickicollab.ediaristas.core.services.storage.adapters.StorageService;
import jakarta.persistence.PreRemove;


public class FotoEntityListener {

   
    private StorageService getStorageService(){
        return SpringContext.getBean(StorageService.class);
        
    } 

    @PreRemove
    private void preRemove(Foto foto){
        getStorageService().apagar(foto.getFilename());
    }
    
}
