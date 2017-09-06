package com.khalid.alloCine.repositories;

import javax.transaction.Transactional;

public interface IEtiquetageDAO {

	void removeActeurFromFilm(int fid, int aid);

	void addActeurToFilm(int fid, int aid);

}