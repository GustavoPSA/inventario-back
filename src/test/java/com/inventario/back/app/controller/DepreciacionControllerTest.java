package com.inventario.back.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario.back.app.domain.dto.DepreciacionDTO;
import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.mapper.DepreciacionMapper;
import com.inventario.back.app.domain.mapper.EquipoMapper;
import com.inventario.back.app.domain.models.Depreciacion;
import com.inventario.back.app.domain.models.Equipo;
import com.inventario.back.app.repository.DepreciacionRepository;
import com.inventario.back.app.repository.EquipoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepreciacionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EquipoMapper equipoMapper;

	@Autowired
	private DepreciacionMapper depreciacionMapper;

	@MockBean
	private EquipoRepository equipoRepository;

	@MockBean
	private DepreciacionRepository depreciacionRepository;

	private EquipoDTO equipoDto;

	private DepreciacionDTO depreciacionDTO;

	private Depreciacion depreciacion;

	@Before
	public void init() {
		equipoDto = getEquipoDto();
		depreciacionDTO = getDepreciacionDTO();
		depreciacion = depreciacionMapper.dtoToEntity(depreciacionDTO);
	}

	private EquipoDTO getEquipoDto() {
		EquipoDTO equipoDto = new EquipoDTO();
		equipoDto.setIdEquipo(1);
		equipoDto.setNumeroSerial("test");
		equipoDto.setNombre("test");
		equipoDto.setDescripcion("test");
		equipoDto.setEstado(1);
		equipoDto.setFechaCompra(new Date());
		equipoDto.setValorCompra(10000000);
		equipoDto.setValorDepreciacion(1000000);
		return equipoDto;
	}

	private DepreciacionDTO getDepreciacionDTO() {
		DepreciacionDTO depreciacionDTO = new DepreciacionDTO();
		depreciacionDTO.setIdDepreciacion(1);
		depreciacionDTO.setEquipo(equipoDto);
		depreciacionDTO.setAno(2023);
		depreciacionDTO.setPorPerdida(2);
		depreciacionDTO.setFechaCreacion(new Date());
		return depreciacionDTO;

	}

	@Test
	public void creacionDepreciacionEstado200() throws Exception {

		Equipo equipo = equipoMapper.dtoToEntity(equipoDto);
		when(equipoRepository.findById(equipoDto.getIdEquipo())).thenReturn(Optional.of(equipo));
		when(depreciacionRepository.save(depreciacion)).thenReturn(depreciacion);

		mockMvc.perform(MockMvcRequestBuilders.post("/depreciacion").content(asJsonString(depreciacionDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void consultaEquiposEstado200() throws Exception {

		when(depreciacionRepository.findAll()).thenReturn(List.of(depreciacion));

		mockMvc.perform(MockMvcRequestBuilders.get("/depreciacion").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void consultaPorIdEstado200() throws Exception {

		int idDepreciacion = 1;

		when(depreciacionRepository.findById(idDepreciacion)).thenReturn(Optional.of(depreciacion));

		mockMvc.perform(MockMvcRequestBuilders.get("/depreciacion/{idDepreciacion}", idDepreciacion)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
