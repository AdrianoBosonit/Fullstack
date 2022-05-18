package ejercicio20;

import ejercicio20.Persona.application.service.PersonaService;
import ejercicio20.Persona.domain.PersonaEntity;
import ejercicio20.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio20.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio20.Persona.repository.PersonaRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonaServiceTest {

    private PersonaInputDTO personaInputDTO;
    private PersonaEntity persona;
    private PersonaOutputDTO personaOutputDTO;
    
    @Mock
    private PersonaRepo personaRepo;

    @InjectMocks
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
        personaInputDTO.setCreated_date(new Date(2022, 3, 24));
        persona = new PersonaEntity(personaInputDTO);
        personaOutputDTO = new PersonaOutputDTO(persona);
    }


    @Test
    public void creaPersonaTest() throws Exception {
        when(personaRepo.save(ArgumentMatchers.any(PersonaEntity.class))).thenReturn(persona);
        PersonaOutputDTO expected = personaService.anadirPersona(personaInputDTO);
        assertEquals(expected, personaOutputDTO);
        verify(personaRepo).save(persona);
    }

    @Test
    public void allPersonaTest() {
        List<PersonaEntity> personas = new ArrayList();
        List<PersonaOutputDTO> personasDto = new ArrayList();
        personas.add(persona);
        personasDto.add(new PersonaOutputDTO(persona));
        given(personaRepo.findAll()).willReturn(personas);
        List<PersonaOutputDTO> expected = personaService.getAll();
        assertEquals(expected, personasDto);
        verify(personaRepo).findAll();
    }

    @Test
    public void deleteByIdPersonaTest() throws Exception {
        when(personaRepo.findById(persona.getId())).thenReturn(Optional.of(persona));
        personaService.removeId(persona.getId());
        verify(personaRepo).deleteById(persona.getId());
    }

    @Test(expected = RuntimeException.class)
    public void deleteIdNoExiste() throws Exception {
        persona.setId(-1);
        given(personaRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
        personaService.removeId(persona.getId());
    }

    @Test
    public void updatePersonaTest() throws Exception {
        personaInputDTO.setName("Probando");
        persona.setName("Probando");
        given(personaRepo.findById(persona.getId())).willReturn(Optional.of(persona));
        personaService.modify(personaInputDTO, persona.getId());
        verify(personaRepo).save(persona);
        verify(personaRepo).findById(persona.getId());
    }

    @Test(expected = RuntimeException.class)
    public void updateIdNoExisteTest() throws Exception {
        persona.setId(-1);
        personaInputDTO.setId(-1);
        given(personaRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
        personaService.modify(personaInputDTO, persona.getId());
    }

    @Test
    public void buscaPorIdTest() {
        when(personaRepo.findById(persona.getId())).thenReturn(Optional.of(persona));
        PersonaOutputDTO expected = personaService.buscaId(personaInputDTO.getId());
        assertEquals(expected, personaOutputDTO);
        verify(personaRepo).findById(persona.getId());
    }

    @Test(expected = RuntimeException.class)
    public void buscaPorIdNoEncontradoTest() {
        persona.setId(1);
        given(personaRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
        personaService.buscaId(persona.getId());
    }

    @Test
    public void buscaPorUsuarioTest() throws Exception {
        List<PersonaEntity> listaConPersona = new ArrayList<>();
        listaConPersona.add(persona);
        List<PersonaOutputDTO> listaDto = new ArrayList<>();
        listaDto.add(new PersonaOutputDTO(persona));
        when(personaRepo.findByUsuario(persona.getUsuario())).thenReturn(listaConPersona);
        List<PersonaOutputDTO> expected = personaService.buscaNombre(personaInputDTO.getUsuario());
        assertEquals(expected, listaDto);
        verify(personaRepo).findByUsuario(persona.getUsuario());
    }
}


