package com.inventario.back.app.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.dto.EquipoEdicionDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EquipoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void creacionEquiposEstado200() throws Exception {

		EquipoDTO equipoDto = new EquipoDTO();
		equipoDto.setNumeroSerial("test");
		equipoDto.setNombre("test");
		equipoDto.setDescripcion("test");
		equipoDto.setEstado(1);
		equipoDto.setFechaCompra(new Date());
		equipoDto.setValorCompra(0.0);
		equipoDto.setValorDepreciacion(0.0);

		mockMvc.perform(MockMvcRequestBuilders.post("/equipo").content(asJsonString(equipoDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void consultaEquiposEstado200() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/equipo").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void consultaPorIdEstado200() throws Exception {

		int idEquipo = 1;

		mockMvc.perform(MockMvcRequestBuilders.get("/equipo/{id}", idEquipo).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void edicionEquiposEstado200() throws Exception {

		EquipoEdicionDTO equipoEdicionDto = new EquipoEdicionDTO();
		equipoEdicionDto.setIdEquipo(1);
		equipoEdicionDto.setNumeroSerial("test 2");
		equipoEdicionDto.setNombre("test 2");
		equipoEdicionDto.setDescripcion("test 2");
		equipoEdicionDto.setEstado(0);
		equipoEdicionDto.setFechaCompra(new Date());
		equipoEdicionDto.setFechaActualiza(new Date());

		mockMvc.perform(MockMvcRequestBuilders.put("/equipo").content(asJsonString(equipoEdicionDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
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
