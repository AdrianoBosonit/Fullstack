package ejercicio20;

import ejercicio20.Persona.application.service.PersonaService;
import ejercicio20.Persona.domain.PersonaEntity;
import ejercicio20.Persona.infrastructure.ControladorPersona;
import ejercicio20.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio20.Persona.infrastructure.dto.output.PersonaOutputDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(ControladorPersona.class)
public class ControllerTest {
    private PersonaInputDTO personaInputDTO;
    private PersonaEntity persona;
    private PersonaOutputDTO personaOutputDTO;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;


    @Before
    public void init() throws Exception {
        personaInputDTO = new PersonaInputDTO();
        personaInputDTO.setId(1);
        personaInputDTO.setName("Testeando");
        personaInputDTO.setUsuario("Usuario");
        personaInputDTO.setCity("Ciudad");
        personaInputDTO.setPassword("Password");
        personaInputDTO.setPersonal_email("abg00044@ujaen.es");
        personaInputDTO.setCompany_email("abg00044@ujaen.es");
        personaInputDTO.setActive(true);
        personaInputDTO.setCreated_date(new Date());
        persona = new PersonaEntity(personaInputDTO);
        personaOutputDTO = new PersonaOutputDTO(persona);
    }


    @Test
    public void createUserTest() throws Exception {
        given(personaService.anadirPersona(personaInputDTO)).willReturn(personaOutputDTO);
        mockMvc.perform(post("/userAdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(persona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona", is(persona.getId())));

    }

    @Test
    public void listAllTest()
            throws Exception {
        List<PersonaOutputDTO> allUsers = Arrays.asList(personaOutputDTO);
        given(personaService.getAll())
                .willReturn(allUsers);
        mockMvc.perform(get("/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idPersona", is(persona.getId())));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        doNothing().when(personaService).removeId(persona.getId());
        mockMvc.perform(delete("/" + persona.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        given(personaService.buscaId(persona.getId())).willReturn(personaOutputDTO);
        mockMvc.perform(get("/" + persona.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona", is(persona.getId())));
    }

    @Test
    public void updatePersonaTest() throws Exception {
        personaInputDTO.setName("Actualizando");
        personaOutputDTO.setName("Actualizando");
        given(personaService.modify(personaInputDTO, persona.getId())).willReturn(personaOutputDTO);
        mockMvc.perform(put("/update/" + persona.getId().toString()).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(personaInputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(personaOutputDTO.getName())));
    }

}
