package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.dao.VCProjectMapper;
import cn.ucmed.general.vc.model.VCProject;
import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.common.util.ModelDataObjectUtil;
import cn.ucmed.common.util.PaginationResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class VCProjectServiceImpl implements VCProjectService {

    private VCProjectMapper vcProjectMapper;

    public void setVcProjectMapper(VCProjectMapper vcProjectMapper) {
        this.vcProjectMapper = vcProjectMapper;
    }

    @Override
    public PaginationResult<VCProjectModel> getPaginatedList(Long pageNo,
                                                             Long pageSize, String searchValue) {

        Long count = vcProjectMapper.countAll(searchValue);
        PaginationResult<VCProjectModel> result = new PaginationResult<VCProjectModel>();
        result.setPageCount(count % pageSize > 0 ? count / pageSize + 1 : count
                / pageSize);
        result.setTotalCount(count);
        result.setList(vcProjectMapper.selectPaginated(
                (pageNo - 1L) * pageSize, pageSize, searchValue));
        return result;
    }

    @Override
    public int update(VCProjectModel model) {
        VCProject record = ModelDataObjectUtil.model2do(model, VCProject.class);
        return vcProjectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int add(VCProjectModel model) {
        VCProject record = ModelDataObjectUtil.model2do(model, VCProject.class);
        return vcProjectMapper.insertSelective(record);
    }

    @Override
    public VCProjectModel findByProjectName(String projectName) {
        return ModelDataObjectUtil.do2model(
                vcProjectMapper.findByProjectName(projectName),
                VCProjectModel.class);
    }

    @Override
    public VCProjectModel findByProjectId(String projectId) {
        return vcProjectMapper.findByProjectId(projectId);
    }

    @Override
    public PaginationResult<VCProjectModel> search(String keyword, Long pageNo,
                                                   Long pageSize) {
        PaginationResult<VCProjectModel> result = new PaginationResult<VCProjectModel>();
        Long count = vcProjectMapper.conutByKeyword(keyword);
        Long pageCount = count % pageSize > 0 ? count / pageSize + 1 : count
                / pageSize;
        List<VCProject> dataObjectList = vcProjectMapper
                .selectPaginatedByKeyword((pageNo - 1) * pageSize, pageSize,
                        keyword);
        List<VCProjectModel> list = new ArrayList<VCProjectModel>();
        if (CollectionUtils.isNotEmpty(dataObjectList)) {
            for (VCProject dataObject : dataObjectList) {
                VCProjectModel model = (VCProjectModel) ModelDataObjectUtil
                        .do2model(dataObject, VCProjectModel.class);
                list.add(model);
            }
        }
        result.setPageCount(pageCount);
        result.setTotalCount(count);
        result.setList(list);
        return result;
    }


    @Override
    public List<VCProjectModel> loadHospitalList() {
        return vcProjectMapper.loadHospitalList();
    }

    @Override
    public List<VCProjectModel> loadProjectList() {
        return vcProjectMapper.loadProjectList();
    }


    @Override
    public List<VCProjectModel> loadProjectListByHospitalName(String hospitalName) {
        return vcProjectMapper.loadProjectListByHospitalName(hospitalName);
    }

    @Override
    public List<VCProjectModel> loadHospitalProjectList() {
        List<VCProjectModel> hlist = vcProjectMapper.loadHospitalList();
        List<VCProjectModel> plist = vcProjectMapper.loadProjectList();


        for (VCProjectModel hl : hlist) {
            if (CollectionUtils.isNotEmpty(plist)) {
                String hname = hl.getHospitalName();
                List<VCProjectModel> temp = new ArrayList<VCProjectModel>();
                for (VCProjectModel pl : plist) {
                    String pname = pl.getHospitalName();
                    if (hname.equals(pname)) {
                        temp.add(pl);
                    }
                }
                hl.setProjectList(temp);
            }

        }

        return hlist;
    }
}
