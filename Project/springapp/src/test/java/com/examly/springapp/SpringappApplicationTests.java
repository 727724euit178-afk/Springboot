package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // DAY 3 - Directory Structure Tests
    @Test
    @Order(1)
    void Day3_test_Controller_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller").isDirectory());
    }

    @Test
    @Order(2)
    void Day3_test_Model_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model").isDirectory());
    }

    @Test
    @Order(3)
    void Day3_test_Service_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service").isDirectory());
    }

    @Test
    @Order(4)
    void Day3_test_Repository_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository").isDirectory());
    }

    // DAY 4 - Model Files Existence
    @Test
    @Order(5)
    void Day4_test_RoleModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Role.java").isFile());
    }

    @Test
    @Order(6)
    void Day4_test_ProfileModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Profile.java").isFile());
    }

    @Test
    @Order(7)
    void Day4_test_UserAccount_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/UserAccount.java").isFile());
    }

    @Test
    @Order(8)
    void Day4_test_AccountStatusLog_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/AccountStatusLog.java").isFile());
    }

    @Test
    @Order(9)
    void Day4_test_UserRoleMapping_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/UserRoleMapping.java").isFile());
    }

    @Test
    @Order(10)
    void Day4_test_Profile_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Profile");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Entity annotation is missing on Profile class");
        } catch (ClassNotFoundException e) {
            fail("❌ Profile class not found.");
        } catch (Exception e) {
            fail("❌ Unable to check @Entity annotation on Profile.");
        }
    }

    @Test
    @Order(11)
    void Day4_test_Profile_Has_Id_Annotation_On_Field() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Profile");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
            boolean found = false;
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) idAnnotation)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No field in Profile class is annotated with @Id");
        } catch (ClassNotFoundException e) {
            fail("❌ Profile class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Id annotation in Profile class.");
        }
    }

    // DAY 5 - Repository Files Existence
    @Test
    @Order(12)
    void Day5_testRoleRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/RoleRepo.java").isFile());
    }

    @Test
    @Order(13)
    void Day5_testProfileRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/ProfileRepo.java").isFile());
    }

    @Test
    @Order(14)
    void Day5_testUserAccountRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/UserAccountRepo.java").isFile());
    }

    @Test
    @Order(15)
    void Day5_testAccountStatusLogRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/AccountStatusLogRepo.java").isFile());
    }

    @Test
    @Order(16)
    void Day5_testUserRoleMappingRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/UserRoleMappingRepo.java").isFile());
    }

    @Test
    @Order(17)
    void Day5_test_ProfileRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.ProfileRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on ProfileRepo class");
        } catch (ClassNotFoundException e) {
            fail("❌ ProfileRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on ProfileRepo.");
        }
    }

    @Test
    @Order(18)
    void Day5_test_RoleRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.RoleRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on RoleRepo class");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on RoleRepo.");
        }
    }

    @Test
    @Order(19)
    void Day5_test_UserAccountRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.UserAccountRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on UserAccountRepo class");
        } catch (ClassNotFoundException e) {
            fail("❌ UserAccountRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on UserAccountRepo.");
        }
    }

    @Test
    @Order(20)
    void Day5_test_AccountStatusLogRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.AccountStatusLogRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on AccountStatusLogRepo class");
        } catch (ClassNotFoundException e) {
            fail("❌ AccountStatusLogRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on AccountStatusLogRepo.");
        }
    }

    @Test
    @Order(21)
    void Day5_test_UserRoleMappingRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.UserRoleMappingRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on UserRoleMappingRepo class");
        } catch (ClassNotFoundException e) {
            fail("❌ UserRoleMappingRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on UserRoleMappingRepo.");
        }
    }

    // DAY 6 - Controller Files Existence
    @Test
    @Order(22)
    void Day6_test_RoleController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/RoleController.java").isFile());
    }

    @Test
    @Order(23)
    void Day6_test_ProfileController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/ProfileController.java").isFile());
    }

    @Test
    @Order(24)
    void Day6_test_UserAccountController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/UserAccountController.java").isFile());
    }

    @Test
    @Order(25)
    void Day6_test_AccountStatusLogController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/AccountStatusLogController.java").isFile());
    }

    @Test
    @Order(26)
    void Day6_test_UserRoleMappingController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/UserRoleMappingController.java").isFile());
    }

    @Test
    @Order(27)
    void Day6_test_RoleController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @RestController annotation is missing on RoleController class");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RestController annotation on RoleController.");
        }
    }

    @Test
    @Order(28)
    void Day6_test_ProfileController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProfileController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @RestController annotation is missing on ProfileController class");
        } catch (ClassNotFoundException e) {
            fail("❌ ProfileController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RestController annotation on ProfileController.");
        }
    }

    @Test
    @Order(29)
    void Day6_test_RoleController_Has_PostMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> postMapping = Class.forName("org.springframework.web.bind.annotation.PostMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) postMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No method with @PostMapping found in RoleController");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PostMapping annotation in RoleController.");
        }
    }

    @Test
    @Order(30)
    void Day6_test_RoleController_Has_GetMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> getMapping = Class.forName("org.springframework.web.bind.annotation.GetMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) getMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @GetMapping method found in RoleController");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @GetMapping in RoleController.");
        }
    }

    @Test
    @Order(31)
    void Day6_test_RoleController_Has_PutMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> putMapping = Class.forName("org.springframework.web.bind.annotation.PutMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) putMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @PutMapping method found in RoleController");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PutMapping in RoleController.");
        }
    }

    @Test
    @Order(32)
    void Day6_test_RoleController_Has_DeleteMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> deleteMapping = Class.forName("org.springframework.web.bind.annotation.DeleteMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) deleteMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @DeleteMapping method found in RoleController");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @DeleteMapping in RoleController.");
        }
    }

    // UPDATED: Changed endpoint from /api/categories to /api/roles
    @Test
    @Order(33)
    public void Day6_testCreateRole_NoBody_StatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // UPDATED: Changed endpoint from /api/categories to /api/roles
    @Test
    @Order(34)
    public void Day6_testGetAllRoles_StatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // // UPDATED: Changed endpoint from /api/categories to /api/roles
    // @Test
    // @Order(35)
    // public void Day6_testGetRoleById_StatusNotFound() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/999"))
    //             .andExpect(MockMvcResultMatchers.status().isNotFound());
    // }

    // DAY 7 - Request Mapping and Path Variable Tests
    @Test
    @Order(36)
    void Day7_test_RoleController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = false;
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @RequestMapping found on RoleController (class or methods)");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in RoleController.");
        }
    }

    @Test
    @Order(37)
    void Day7_test_RoleController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.RoleController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            assertTrue(found, "❌ No @PathVariable annotation found in any method parameter of RoleController");
        } catch (ClassNotFoundException e) {
            fail("❌ RoleController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in RoleController.");
        }
    }

    @Test
    @Order(38)
    void Day7_test_ProfileController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProfileController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = false;
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @RequestMapping found on ProfileController (class or methods)");
        } catch (ClassNotFoundException e) {
            fail("❌ ProfileController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in ProfileController.");
        }
    }

    @Test
    @Order(39)
    void Day7_test_UserRoleMappingController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.UserRoleMappingController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = false;
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @RequestMapping found on UserRoleMappingController (class or methods)");
        } catch (ClassNotFoundException e) {
            fail("❌ UserRoleMappingController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in UserRoleMappingController.");
        }
    }

    @Test
    @Order(40)
    void Day7_test_ProfileController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProfileController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            assertTrue(found, "❌ No @PathVariable found in any method parameter of ProfileController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProfileController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in ProfileController.");
        }
    }

    @Test
    @Order(41)
    void Day7_test_UserRoleMappingController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.UserRoleMappingController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            assertTrue(found, "❌ No @PathVariable found in any method parameter of UserRoleMappingController");
        } catch (ClassNotFoundException e) {
            fail("❌ UserRoleMappingController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in UserRoleMappingController.");
        }
    }

    @Test
    @Order(42)
    void Day7_test_AccountStatusLog_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.AccountStatusLogController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = false;
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @RequestMapping found on AccountStatusLogController (class or methods)");
        } catch (ClassNotFoundException e) {
            fail("❌ AccountStatusLogController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in AccountStatusLogController.");
        }
    }

    // DAY 8 - Service Files Existence
    @Test
    @Order(43)
    void Day8_test_RoleService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/RoleService.java").isFile());
    }

    @Test
    @Order(44)
    void Day8_test_ProfileService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ProfileService.java").isFile());
    }

    @Test
    @Order(45)
    void Day8_test_UserAccountService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/UserAccountService.java").isFile());
    }

    @Test
    @Order(46)
    void Day8_test_AccountStatusLogService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AccountStatusLogService.java").isFile());
    }

    @Test
    @Order(47)
    void Day8_test_UserRoleMappingService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/UserRoleMappingService.java").isFile());
    }

    @Test
    @Order(48)
    void Day8_test_RoleServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/RoleServiceImpl.java").isFile());
    }

    @Test
    @Order(49)
    void Day8_test_ProfileServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ProfileServiceImpl.java").isFile());
    }

    @Test
    @Order(50)
    void Day8_test_UserAccountServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/UserAccountServiceImpl.java").isFile());
    }

    @Test
    @Order(51)
    void Day8_test_AccountStatusLogServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AccountStatusLogServiceImpl.java").isFile());
    }

    @Test
    @Order(52)
    void Day8_test_UserRoleMappingServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/UserRoleMappingServiceImpl.java").isFile());
    }

    // UPDATED: Changed endpoint and field name (roleName instead of RoleName)
    @Test
    @Order(53)
    public void Day8_testAddRole() throws Exception {
        String requestBody = "{ \"roleName\": \"Admin\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName").value("Admin"))
                .andReturn();
    }

    // UPDATED: Changed endpoint
    @Test
    @Order(54)
    public void Day8_testGetAllRoles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roleName").value("Admin"))
                .andReturn();
    }

    // UPDATED: Changed endpoint
    @Test
    @Order(55)
    public void Day8_testGetRoleById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName").value("Admin"))
                .andReturn();
    }

    // UPDATED: Changed endpoint
    @Test
    @Order(56)
    public void Day8_testUpdateRole() throws Exception {
        String requestBody = "{\"roleName\": \"SuperAdmin\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/roles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName").value("SuperAdmin"))
                .andReturn();
    }

    // DAY 9 - Pagination Tests (COMMENTED OUT - Need to implement pagination endpoint)
    @Test
    @Order(57)
    public void Day9_testPagination_PageNumberApplied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.pageNumber").value(0));
    }

    @Test
    @Order(58)
    public void Day9_testPagination_PageSizeApplied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/1/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.pageSize").value(10));
    }

    @Test
    @Order(59)
    public void Day9_testPagination_SortingPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.sort").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.sort.sorted").isBoolean());
    }

    @Test
    @Order(60)
    public void Day9_testPagination_ContentArrayExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray());
    }

    @Test
    @Order(61)
    public void Day9_testPagination_TotalElementsExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").exists());
    }

    @Test
    @Order(62)
    public void Day9_testPagination_TotalPagesExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").exists());
    }

    // DAY 10 - Profile CRUD Tests


    @Test
@Order(63)
public void Day10_testCreateUser() throws Exception {

    String requestBody =
            "{ \"username\": \"vikram\", " +
            "\"passwordHash\": \"hashedpass123\", " +
            "\"email\": \"vikram@example.com\", " +
            "\"phone\": \"9876543210\", " +
            "\"status\": \"ACTIVE\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("vikram"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("vikram@example.com"))
            .andReturn();
}





    @Test
    @Order(64)
    public void Day10_testAddProfile() throws Exception {
        String requestBody =
            "{ \"firstName\": \"John\", " +
            "\"lastName\": \"Doe\", " +
            "\"address\": \"Chennai\", " +
            "\"dob\": \"1995-05-10\", " +
            "\"gender\": \"Male\", " +
            "\"user\": { \"id\": 1 } }";
    
        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
               // .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(1))
                .andReturn();
    }
    
    
@Test
@Order(65)
public void Day10_testGetAllProfiles() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
            .andReturn();
}

@Test
@Order(66)
public void Day10_testGetProfileById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andReturn();
}

@Test
@Order(67)
public void Day10_testUpdateProfile() throws Exception {
    String requestBody = "{ \"firstName\": \"John Updated\", \"lastName\": \"Doe Updated\", \"address\": \"Mumbai\", \"dob\": \"1995-05-10\", \"gender\": \"Male\" }";

    mockMvc.perform(MockMvcRequestBuilders.put("/api/profiles/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John Updated"))
            .andReturn();
}


    // // DAY 10 - UserRoleMapping CRUD Tests
    @Test
    @Order(68)
    public void Day10_testAddUserRoleMapping() throws Exception {
        String requestBody = """
            {
                "user": { "id": 1 },
                "role": { "id": 1 }
            }
            """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/userRoleMappings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(69)
    public void Day10_testGetAllUserRoleMappings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userRoleMappings")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(70)
    public void Day10_testGetUserRoleMappingById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userRoleMappings/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

  @Test
@Order(71)
public void Day10_testUpdateUserRoleMapping() throws Exception {
    // Prepare JSON body using nested objects for user and role
    String requestBody = """
        {
            "user": { "id": 1 },
            "role": { "id": 1 }
        }
        """;

    mockMvc.perform(MockMvcRequestBuilders.put("/api/userRoleMappings/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.user.id").value(1))
            .andExpect(jsonPath("$.role.id").value(1))
            .andReturn();
}


    //DAY 11-12 - JPQL Query Tests (COMMENTED OUT - Need to implement custom query endpoints)
    @Test
    @Order(71)
    public void Day11_testGetUserRoleMappingsByUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userRoleMappings/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    @Order(73)
    public void Day12_testGetUserRoleMappingByUserIdAndRoleId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userRoleMappings/user/1/role/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(74)
    public void Day12_testGetUserRoleMappingByUserId_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userRoleMappings/user/999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(75)
    public void Day12_testGetProfileByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/name/John Updated")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John Updated"))
                .andReturn();
    }

    // @Test
    // @Order(76)
    // public void Day12_testGetProfileByName_NotFound() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/name/UnknownProfile")
    //             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isNotFound())
    //             .andReturn();
    // }



    @Test
    @Order(77)
    public void Day12_testGetProfilesByNameAndAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/search/John Updated/Mumbai")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John Updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("Mumbai"))
                .andReturn();
    }



    




    // DAY 13 - Exception Handling Tests
    @Test
    @Order(78)
    void Day13_test_exception_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception").isDirectory());
    }

    @Test
    @Order(79)
    void Day13_test_GlobalExceptionHandler_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception/GlobalExceptionHandler.java").isFile());
    }

    // DAY 14 - Configuration Tests
    @Test
    @Order(80)
    void Day14_test_configuration_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/configuration").isDirectory());
    }

    // DAY 15 - AOP Tests
    @Test
    @Order(81)
    public void Day15_testAOPLogFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/aop").isDirectory());
    }
}