package Services;

import java.util.List;
import Entities.Module;
import Repositories.ModuleRepository;

public class ModuleService {
    private ModuleRepository moduleRepository;

    public ModuleService() {
        this.moduleRepository = new ModuleRepository();
    }

    public void ajouterModule(Module module) {
        moduleRepository.insert(module);
    }

    public List<Module> listerModules() {
        return moduleRepository.selectAll();
    }

    public Module trouverModuleParId(int idModule) {
        return null;
    }

}

