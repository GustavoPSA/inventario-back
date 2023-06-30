package com.inventario.back.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.back.app.domain.dto.DepreciacionDTO;
import com.inventario.back.app.domain.mapper.DepreciacionMapper;
import com.inventario.back.app.domain.models.Depreciacion;
import com.inventario.back.app.domain.models.Equipo;
import com.inventario.back.app.exception.NotFoundException;
import com.inventario.back.app.repository.DepreciacionRepository;
import com.inventario.back.app.repository.EquipoRepository;
import com.inventario.back.app.service.IDepreciacionService;

@Service
public class DepreciacionServiceImpl implements IDepreciacionService {

	@Autowired
	private DepreciacionRepository depreciacionRepository;

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private DepreciacionMapper depreciacionMapper;

	@Override
	@Transactional(readOnly = true)
	public List<DepreciacionDTO> list() {

		List<Depreciacion> depreciaciones = depreciacionRepository.findAll();

		if (depreciaciones.isEmpty()) {
			throw new NotFoundException("No se encontraron Depreciaciones");
		}

		return depreciacionMapper.toListDTO(depreciaciones);
	}

	@Override
	@Transactional(readOnly = true)
	public DepreciacionDTO getById(int idDepreciacion) {
		// TODO Auto-generated method stub
		Optional<Depreciacion> depreciacionOptional = depreciacionRepository.findById(idDepreciacion);

		if (!depreciacionOptional.isPresent()) {
			throw new NotFoundException("No se encontro la Depreciacion con ID: " + idDepreciacion);
		}

		return depreciacionMapper.entityToDto(depreciacionOptional.get());
	}

	@Override
	@Transactional(readOnly = false)
	public DepreciacionDTO add(DepreciacionDTO depreciacionDTO) {

		Equipo equipo = equipoRepository.findById(depreciacionDTO.getEquipo().getIdEquipo()).get();

		double valorPerdida = equipo.getValorDepreciacion() * depreciacionDTO.getPorPerdida() / 100;
		double valorDepreciacion = equipo.getValorDepreciacion() - valorPerdida;
		equipo.setValorDepreciacion(valorDepreciacion);

		equipoRepository.save(equipo);

		// TODO Auto-generated method stub
		return depreciacionMapper
				.entityToDto(depreciacionRepository.save(depreciacionMapper.dtoToEntity(depreciacionDTO)));
	}

}
