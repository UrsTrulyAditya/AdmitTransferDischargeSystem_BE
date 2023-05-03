package com.stackroute.tpaMicroService.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tpaMicroService.exceptions.DuplicateTpaException;
import com.stackroute.tpaMicroService.exceptions.TPANotDeletedException;
import com.stackroute.tpaMicroService.exceptions.TPANotFoundException;
import com.stackroute.tpaMicroService.model.TPA;
import com.stackroute.tpaMicroService.repository.TPARepository;

@Service
public class TPAServiceImpl implements TPAService {

	@Autowired
	private TPARepository tpaRepository;

	@Override
	public TPA login(String email, String password) {

		Optional<TPA> optionalTPA = tpaRepository.findByEmailAndPassword(email, password);

		return optionalTPA.orElse(null);

	}


	@Override
    public TPA register(TPA tpa) {     
        Optional<TPA> optionalAdminByEmail = tpaRepository.findByEmail(tpa.getEmail());
        if (optionalAdminByEmail.isPresent()) {
            throw new DuplicateTpaException();
        }
        return tpaRepository.save(tpa);
    }

	@Override
	public TPA updateTPA(int id, TPA tpa) {

		Optional<TPA> optionalTPA = tpaRepository.findById(id);

		if (optionalTPA.isPresent()) {

			TPA existingTPA = optionalTPA.get();

			existingTPA.setName(tpa.getName());

			existingTPA.setPassword(tpa.getPassword());

			existingTPA.setHospitalId(tpa.getHospitalId());

			existingTPA.setEmail(tpa.getEmail());

			return tpaRepository.save(existingTPA);

		}

		return null;

	}

	@Override
	public List<TPA> viewTpas() {
		List<TPA> tpas = tpaRepository.findAll();
		if (tpas.isEmpty()) {
			throw new TPANotFoundException();
		}
		return tpas;
	}

	@Override
	public void deleteTpaById(int id) {
		Optional<TPA> tpa = tpaRepository.findById(id);
		if (tpa.isPresent()) {
			tpaRepository.deleteById(id);
		} else {
			throw new TPANotDeletedException();
		}
	}

	@Override
	public TPA viewTpaById(int id) throws TPANotFoundException {
		Optional<TPA> tpa = tpaRepository.findById(id);
		if (tpa.isPresent()) {
			return tpa.get();
		} else {
			throw new TPANotFoundException();
		}
	}

}