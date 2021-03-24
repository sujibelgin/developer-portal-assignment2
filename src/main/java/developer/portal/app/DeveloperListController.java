package developer.portal.app;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope (value = "session")
@Component (value = "developerList")
@ELBeanName(value = "developerList")
@Join(path = "/", to = "/developer-list.jsf")

/* DeveloperListController class for listing and adding the new developers.
 This class methods save and getDeveloper*/
public class DeveloperListController {

    @Autowired
    private DeveloperRepository developerRepository;

    private List<Developer> developers;
    private Developer developer = new Developer();

    @Deferred
    @RequestAction
    @IgnorePostback

    /* This controller has a method called loadData that is annotated with @Deferred,
    @RequestAction, and @IgnorePostback. These annotations are needed to load the
    collection of developers from developerRepository before rendering the interface.  */
    public void loadData() {
        developers = developerRepository.findAll();
    }

     /* The save() method will be called by a JSF button to save a newly added developer to the
      database after saving the developer details it will clear the Text*/
    public String save() {
        developerRepository.save(developer);
        developer = new Developer();

        /*The save() method redirect to the developer-list.xhtml page the interface
        that lists developer persisted in HSQLDB*/
        return "/developer-list.xhtml?faces-redirect=true";
    }

    /*This getDeveloper() method will be used by the interface to tie the inputs on the form to an
    instance of Developer*/
    public Developer getDeveloper() {
        return developer;
    }


    /*This getDevelopers() method will be used by the interface to tie the inputs on the form to an
       instance of DeveloperList*/
    public List<Developer> getDevelopers() {
        return developers;
    }
}
