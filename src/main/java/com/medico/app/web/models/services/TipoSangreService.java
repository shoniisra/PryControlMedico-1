package com.medico.app.web.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.app.web.models.dao.ITipoSangreDAO;
import com.medico.app.web.models.entities.TipoSangre;;

@Service
public class TipoSangreService implements ITipoSangreService {

    @Autowired
    private ITipoSangreDAO dao;


    @Override
    public TipoSangre findById(Integer id) {
        // TODO Auto-generated method stub
        return dao.findById(id).get();
    }

    @Override
    public List<TipoSangre> findAll() {
        // TODO Auto-generated method stub
        return (List<TipoSangre>) dao.findAll();
    }

}

