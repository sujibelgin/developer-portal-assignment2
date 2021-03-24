package developer.portal.app;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@Scope (value = "session")
@Component (value = "developerView")
@ELBeanName(value = "developerView")
@Join(path = "/developer/{id}", to = "/developer-view.jsf")
public class DeveloperViewController {

    //creating variable for developerRepository
    @Autowired
    private DeveloperRepository developerRepository;

    //Creating developer variable for the type Developer
    private Developer developer;

    //creating newSkill variable for adding newSkill for the developer
    private String newSkill = "";

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        //Reading the developer Id parameter from request.
        String id = request.getParameter("id");

        // Reading the developer data ( of corresponding developer Id ) from database.
        developer = developerRepository.findOne(Long.parseLong(id));

    }

    /*This getDeveloper() method will be used by the interface to tie the inputs on the form to an
   instance of Developer*/
    public Developer getDeveloper() {
        return developer;
    }

    /*This getNewSkill() method will be used by the interface to tie the inputs on the form to  an
      instance of Developer*/
    public String getNewSkill() {
        return newSkill;
    }


    // This method adds the newSkill and that will be concatenated with the existing skill
    public void setNewSkill(String value) {
        newSkill = value;
    }



    public String addSkill() {
        developer.setSkills(developer.getSkills() + "," + newSkill);
        //This sava()function will save the data to database.
        developerRepository.save(developer);
        newSkill = "";
        return "/developer-list.xhtml?faces-redirect=true";//redirecting to the developer-list.xhtml page
    }
}
