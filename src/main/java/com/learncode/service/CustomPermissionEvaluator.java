package com.learncode.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	NguoiDungService nguoidungService;
	
	@PersistenceContext
	EntityManager em;
	
	private final String nativeQueryFindPermission = "select cn.machucnang, cn.id from qtht_chucnang cn\r\n" + 
			"	left join qtht_nhomnguoidungchucnang nn_cn on nn_cn.idchucnang = cn.id\r\n" + 
			"	left join qtht_nhomnguoidung nn on nn.id = nn_cn.idnhom\r\n" + 
			"	left join qtht_nguoidungvanhomnguoidung nd_nn on nd_nn.idnhom = nn.id\r\n" + 
			"	left join qtht_vaitrovachucnang vt_cn on vt_cn.idchucnang = cn.id\r\n" + 
			"	left join qtht_vaitro vt on vt.id = vt_cn.idvaitro\r\n" + 
			"	left join qtht_nguoidungvavaitro nd_vt on  nd_vt.idvaitro = vt.id\r\n" + 
			"	left join qtht_nguoidung nd on nd_nn.idnguoidung = nd.id or nd_vt.idnguoidung = nd.id\r\n" + 
			"	where nd.tennguoidung = ?\r\n" + 
			"	group by cn.id";
	
	@Override
	public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
//		System.out.println("per: "+permission + " -> " + auth.getName());
		if ((auth != null) && (permission instanceof String)) {
			List<Object[]> resultList = (List<Object[]>) this.em.createNativeQuery(this.nativeQueryFindPermission).setParameter(1, auth.getName()).getResultList();
			List<Object[]> data = resultList;
			for(Object[] per: data) {
				if(per[0].toString().equals(String.valueOf(permission))) {
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
		System.out.println("per: "+permission);
		return false;
	}


}
