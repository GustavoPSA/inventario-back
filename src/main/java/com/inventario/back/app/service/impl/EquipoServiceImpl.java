package com.inventario.back.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.dto.EquipoEdicionDTO;
import com.inventario.back.app.domain.mapper.EquipoEdicionMapper;
import com.inventario.back.app.domain.mapper.EquipoMapper;
import com.inventario.back.app.domain.models.Equipo;
import com.inventario.back.app.exception.NotFoundException;
import com.inventario.back.app.repository.EquipoRepository;
import com.inventario.back.app.service.IEquipoService;

@Service
public class EquipoServiceImpl implements IEquipoService {

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private EquipoMapper equipoMapper;

	@Autowired
	private EquipoEdicionMapper equipoEdicionMapper;

	@Override
	@Transactional(readOnly = true)
	public List<EquipoDTO> list() {

		List<Equipo> equipos = equipoRepository.findAll();

		if (equipos.isEmpty()) {
			throw new NotFoundException("No se encontraron Equipos");
		}

		return equipoMapper.toListDTO(equipos);
	}

	@Override
	@Transactional(readOnly = true)
	public EquipoDTO getById(int idEquipo) {
		// TODO Auto-generated method stub
		Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

		if (!equipoOptional.isPresent()) {
			throw new NotFoundException("No se encontro el Equipo con ID: " + idEquipo);
		}

		return equipoMapper.entityToDto(equipoOptional.get());
	}

	@Override
	@Transactional(readOnly = false)
	public EquipoDTO add(EquipoDTO equipoDTO) {

		Equipo equipo = equipoMapper.dtoToEntity(equipoDTO);
		equipo.setValorDepreciacion(equipo.getValorCompra());

		// TODO Auto-generated method stub
		return equipoMapper.entityToDto(equipoRepository.save(equipo));
	}

	@Override
	@Transactional(readOnly = false)
	public EquipoEdicionDTO edit(EquipoEdicionDTO equipoEdicionDTO) {
		// TODO Auto-generated method stub

		if (!equipoRepository.existsById(equipoEdicionDTO.getIdEquipo())) {
			throw new NotFoundException("No se encontro el Equipo con ID: " + equipoEdicionDTO.getIdEquipo());
		}

		Equipo equipoActual = equipoRepository.findById(equipoEdicionDTO.getIdEquipo()).get();

		Equipo equipoNuevo = equipoEdicionMapper.dtoToEntity(equipoEdicionDTO);

		equipoNuevo.setValorCompra(equipoActual.getValorCompra());
		equipoNuevo.setValorDepreciacion(equipoActual.getValorDepreciacion());

		return equipoEdicionMapper.entityToDto(equipoRepository.save(equipoNuevo));

	}
}
