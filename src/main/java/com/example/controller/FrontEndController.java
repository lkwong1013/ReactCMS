package com.example.controller;

import com.example.config.EnvSetting;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by LKW on 2016/10/28.
 */

@Controller
@RequestMapping("/")
public class FrontEndController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

//    private FrontEndService frontEndService;
//
//    @Autowired
//    UserAccountService userAccountService;

    @Autowired
    private EnvSetting envSetting;

//    @Autowired
//    public FrontEndController(FrontEndService frontEndService) {
//        super(frontEndService);
//        this.frontEndService = frontEndService;
//    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) throws Exception {
        //model.addAttribute("employee", employee);
//        model.addAttribute("baseUrl", request.getContextPath());
//        String welcome = messageSource.getMessage("msg.loginFailed",null, LocaleContextHolder.getLocale());
//        String test = BaseUtils.getMessage("msg.loginFailed");
        String test = envSetting.getSetting("filelocation");
        return "index";
    }

    @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
    public String testPage(ModelMap model, HttpServletRequest request) throws Exception {
        return "test";
    }



    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login (ModelMap model, HttpServletRequest request) {
        return "loginPage";
    }

    @RequestMapping(value = { "/fullLogin" }, method = RequestMethod.GET)
    public String fullLogin (ModelMap model, HttpServletRequest request) {

        model.addAttribute("loginActionUrl", "/loginAction/login");
        this.getUserDetails();
//        List<UserRole> roleList = this.userRoleService.findAll();
//        log.info(roleList);

        return "fullLoginPage";
    }

    @RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
    public String dashboard (ModelMap model, HttpServletRequest request) throws Exception {
        return "dashboard";
    }

    @RequestMapping(value = { "/datalist" }, method = RequestMethod.GET)
    public String dataList (ModelMap model, HttpServletRequest request) throws Exception {
        return "list_sample";
    }

    private void getUserDetails() {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("");

    }

//    @ResponseBody
//    @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
//    public BaseResponseObj test (HttpServletRequest request, HttpSession session) {
//
//        BaseResponseObj responseObj = new BaseResponseObj();
//        //UserLoginSession loginSession;
//
//        UserLoginSession loginSession = (UserLoginSession)session.getAttribute("loginSession");
//        if (loginSession != null) {
//            responseObj.setMessage(loginSession.getUserId().toString()+" : "+loginSession.getExpiryTime());
//        }
//        return responseObj;
//    }
//
//    @RequestMapping(value = { "/denied" }, method = RequestMethod.GET)
//    public String permissionDenied (ModelMap model, HttpServletRequest request) {
//        return "permission_denied";
//    }
//
//    private void getUserDetails() {
//        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
//                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        System.out.println("");
//
//    }
//
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/fullLogin?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/clearFilter", method = RequestMethod.POST)
//    public BaseResponseObj clearFilter (HttpServletRequest request, HttpServletResponse response, String filterName) {
//
//        String logPrefix = "clearFilter(): ";
//        if (StringUtils.isNotBlank(filterName)) {
//            // Do clear filter
//            request.getSession().removeAttribute(filterName);
//            log.debug(logPrefix + "Cleared search filter - "+filterName);
//        }
//        return new BaseResponseObj();
//
//    }
//
//
//    @RequestMapping(value="/test-websocket", method = RequestMethod.GET)
//    public String testWebSocket (ModelMap map, HttpServletRequest request, HttpServletResponse response, String filterName) {
//        return "im/test";
//    }
//
//    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
//    public String changePassword(ModelMap model, HttpServletRequest request) {
//        baseUrl = request.getContextPath();
//        model.addAttribute("submitUrl", baseUrl + "/changePasswordSubmit");
//        return "changePassword";
//    }
//
//    @RequestMapping(value = {"/changePasswordSubmit"}, method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponseObj changePassword(ChangePasswordForm newPasswordData, HttpServletRequest request) throws Exception {
//        return this.userAccountService.changeAccountPassword(newPasswordData, request);
//    }
//
//    /*--- File Manager Test ---*/
//    @RequestMapping(value="/test-file-manager", method = RequestMethod.GET)
//    public String testFileManager(ModelMap map) {
//
//        return "file_list";
//
//    }
//
//    @RequestMapping(value="/test-file-download", method = RequestMethod.GET)
//    public void testFileDownload(HttpServletResponse response) throws Exception {
//
//        try {
//            final String EXTERNAL_FILE_PATH = "E:/Dev Docs/TheMultiplier/TM Icon.jpg";
//            File file = null;
//            file = new File(EXTERNAL_FILE_PATH);
//
//            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//            if (mimeType == null) {
//                System.out.println("mimetype is not detectable, will take default");
//                mimeType = "application/octet-stream";
//            }
//
//            System.out.println("mimetype : " + mimeType);
//
//            response.setContentType(mimeType);
//
//        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
//            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
//            //response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
//
//
//        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
//            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
//
//            response.setContentLength((int) file.length());
//
//            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//
//            //Copy bytes from source to destination(outputstream in this example), closes both streams.
//            FileCopyUtils.copy(inputStream, response.getOutputStream());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*--- Cache test ---*/
//    @RequestMapping(value = "/test-cache", method = RequestMethod.GET)
//    public String testCache(ModelMap map, HttpServletRequest request) {
//        map.addAttribute("currentCache", CACHE_TEST);
//        baseUrl = request.getContextPath();
//        map.addAttribute("submitUrl", baseUrl + "/update-test-cache");
//        return  "test/test_cache";
//    }
//
//    @RequestMapping(value = "/update-test-cache", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponseObj updateTestCache(TestConstantForm formData, HttpServletRequest request) throws Exception {
//
//        if (StringUtils.isNotBlank(formData.getCacheContent())) {
//            CACHE_TEST = formData.getCacheContent();
//        }
//        return new BaseResponseObj();
//    }


}
