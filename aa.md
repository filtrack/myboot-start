#####车辆预约，检测当时段司机是否预约
问题:查询条件1字段错误，getReservationNumber：carReservationDTO.getCertificateNumber

处理方式：解决字段匹配问题，验证是否能确定司机是否空闲


######车辆预约，检测当时段司机是否预约
问题:查询条件1字段错误，getReservationNumber：carReservationDTO.getCertificateNumber

处理方式：解决字段匹配问题，验证是否能确定司机是否空闲


######1607 ~ 1616

```
//当前时段司机是否预约
        int driver = this.count(Wrappers.<CarReservation>lambdaQuery()
                .eq(CarReservation::getReservationNumber, carReservationDTO.getCertificateNumber())
                .eq(CarReservation::getReservationDate, strDate)
                .eq(CarReservation::getBsTimeline, bsTimeline)
                .notIn(CarReservation::getState, CarReservationState.COMPLETED.getState(), CarReservationState.CANCEL.getState()));
        if (driver > 0) {
            log.error("司机备案编号:[{}],司机在时段[{}]已经预约过了!", carReservationDTO.getCertificateNumber(), bsTimeline);
            throw new BusinessException("驾驶员当前时段已预约");
        }
```


#####车辆预约，取消预约
问题：对象使用后再次设置原对象无效
处理方式：setBill() 放在bill属性值完成之后，避免后边使用空置

######车辆预约，取消预约
问题：对象使用后再次设置原对象无效
处理方式：setBill() 放在bill属性值完成之后，避免后边使用空置

######77 ~ 89

```
CarGoAndWmsDto carGoAndWmsDto = new CarGoAndWmsDto();
            carGoAndWmsDto.setName(carReservationTypeEnum.getName());
            if (null != auditState) {
                carGoAndWmsDto.setAuditState(Long.parseLong(auditState));
            }
            carGoAndWmsDto.setBill(bill);
            if (AppointmentOrderConstants.IEFLAG_IMPORT.equals(bill.getIeType())) {
                CustAppointmentImport custAppointmentImport = custAppointmentImportService.getCustAppointmentImportByBillNo(bill.getBillNo()).get(0);
                bill.setTradeMode(custAppointmentImport.getTradeMode());
            } else {
                CustAppointmentExport export = custAppointmentExportService.getCustAppointmentExportByBillno(bill.getBillNo());
                bill.setTradeMode(export.getTradeMode());
            }
```


#####车辆预约，取消预约
问题:未有调度数据时，进行查询删除，是否合理
处理方法：审核通过后，生成了调度队列，取消才需要删除调度数据，加入状态处理，不建议全范围处理

######车辆预约，取消预约
问题:未有调度数据时，进行查询删除，是否合理
处理方法：审核通过后，生成了调度队列，取消才需要删除调度数据，加入状态处理，不建议全范围处理

######612 ~ 613

```
        log.info("移除[{}]关联的调度队列信息", number.getReservationNumber());
        carDispatchQueueService.remove(Wrappers.<CarDispatchQueue>lambdaUpdate().eq(CarDispatchQueue::getReservationNumber, number.getReservationNumber()));
```


#####运营端预约列表
问题：此处遍历查询的结果，却没有使用
处理方法：删除无用消耗性能代码

######运营端预约列表
问题：此处遍历查询的结果，却没有使用
处理方法：删除无用消耗性能代码

######174 ~ 188

```
for (CarReservationManageVO carReservationManageVO : list) {
            int count = carDispatchQueueService.count(Wrappers.<CarDispatchQueue>lambdaQuery().eq(CarDispatchQueue::getReservationNumber, carReservationManageVO.getReservationNumber()));
            if (count == 1) {
                CarDispatchQueueDetailVO queue = carDispatchQueueService.getCarDispatchQueueById(null, carReservationManageVO.getReservationNumber()).get(0);
                carReservationManageVO.setCarDispatchDate(queue.getCarDispatchDate().toString());
                carReservationManageVO.setCarDisaptchTimeline(queue.getCarDisaptchTimeline());
            }

            int i = carDispatchQueueService.count(Wrappers.<CarDispatchQueue>lambdaQuery()
                    .ne(CarDispatchQueue::getState, CallStatusEnum.Cancel.state())
                    .eq(CarDispatchQueue::getReservationNumber, carReservationManageVO.getReservationNumber()));
            if (count != 0 && count == i) {
                carReservationManageVO.setControl(AvailableStatusEnum.ENABLE.state());
            }
        }
```


#####车辆预约
问题：获取码头当前时段最大预约量，参数名称具有极大问题，且后续做了业务判断
建议：变更具有意义的参数名称，例如 timeMaxNum

######车辆预约
问题：获取码头当前时段最大预约量，参数名称具有极大问题，且后续做了业务判断
建议：变更具有意义的参数名称，例如 timeMaxNum

######386 ~ 386

```
String dateTime = sysConfigService.getConfigByKey(SysConfigConstants.DOCKER_TIMEPERIOD_MAX_DISPATCH);
```


#####车辆预约，重新预约
问题1: 第一行 null == number.getDispatchState()直接使用
问题2: 什么情况或状态下认为可以重新预约

处理方式: 1.首先判断非空
2.建议采用同意字段标记整体状态

######车辆预约，重新预约
问题1: 第一行 null == number.getDispatchState()直接使用
问题2: 什么情况或状态下认为可以重新预约

处理方式: 1.首先判断非空
2.建议采用同意字段标记整体状态

######448 ~ 459

```
if ( CallStatusEnum.WAIT_YOUR_TURN.state().equals(number.getDispatchState().intValue())
                    || null == number.getDispatchState()
                    || CallStatusEnum.Cancel.state().equals(number.getDispatchState().intValue())) {
                if (null == number.getAuditState() || CarAuditStateEnum.FAIL_AUDIT.state().equals(number.getAuditState().toString()) ||
                        CarAuditStateEnum.WAIT_AUDIT.state().equals(number.getAuditState().toString()) || (CarAuditStateEnum.SUCCESS_AUDIT.state().equals(number.getAuditState().toString()))) {
                    //数据校验
                    carReservation.setUserId(ShiroUtils.getUserId());
                    carReservationService.dataValidation(carReservation);
                    carReservation.setChangeFlag(ChangeFlagEnum.UPDATE.operateCode());
                    return toAjax(carReservationService.reservationAddOrUpdate(carReservation, ShiroUtils.getUserId()));
                }
            }
```


#####车辆预约，取消预约
问题1: 基础验证太靠后
问题2: OPERATE_TIME_OUT("作业时间不足", "0") 非审核状态

处理方式： 1.提高最高早时间判断
2.合理使用状态

######车辆预约，取消预约
问题1: 基础验证太靠后
问题2: OPERATE_TIME_OUT("作业时间不足", "0") 非审核状态

处理方式： 1.提高最高早时间判断
2.合理使用状态

######642 ~ 653

```
        //变更后获取最新的车辆预约信息
        if (!StringUtils.isEmpty(carReservation.getCancelReason())) {
            number.setCancelReason(carReservation.getCancelReason());
        }
        if (!StringUtils.isEmpty(carReservation.getWriteOff())) {
            number.setWriteOff(carReservation.getWriteOff());
        }

        number.setState(CarReservationEnum.CANCEL.state());
        if (null != carReservation.getAuditState() && CarAuditStateEnum.OPERATE_TIME_OUT.state().equals(carReservation.getAuditState().toString())) {
            number.setAuditState(Long.parseLong(CarAuditStateEnum.OPERATE_TIME_OUT.state()));
        }
```


#####车辆预约，时间格式校验
问题:时间校验这种简单逻辑执行太滞后

处理方式:放在复杂逻辑校验之前处理

######车辆预约，时间格式校验
问题:时间校验这种简单逻辑执行太滞后

处理方式:放在复杂逻辑校验之前处理

######1381 ~ 1385

```
//时间格式校验
        if (!dateFormatValidation(carReservationDTO.getReservationDate())) {
            log.error("时间不能是过去时间,请修改后提交!");
            throw new BusinessException("预约时间错误");
        }
```


#####园区，预约详情
问题:此方法对简单操作过度处理
处理方法:针对不同的操作拆分处理，避免不必要的操作

######园区，预约详情
问题:此方法对简单操作过度处理
处理方法:针对不同的操作拆分处理，避免不必要的操作

######143 ~ 143

```
resultParamMap(id, mmap, reservationNumber);
```


#####车辆预约，选择运单

问题1: if (null == tradeMode) { type = ""; } 如果为空还需要继续执行吗 ?
问题2: else if (BusTypeEnum.KAYE.code().equals(tradeMode)) { type = BusTypeEnum.KAYE.state().toString(); } 都判断一致了，还需要重新复制吗？

处理方法:
问题一:判断非空非空字符，直接return
问题二:删除该代码


######车辆预约，选择运单

问题1: if (null == tradeMode) { type = ""; } 如果为空还需要继续执行吗 ?
问题2: else if (BusTypeEnum.KAYE.code().equals(tradeMode)) { type = BusTypeEnum.KAYE.state().toString(); } 都判断一致了，还需要重新复制吗？

处理方法:
问题一:判断非空非空字符，直接return
问题二:删除该代码


######1171 ~ 1178

```
String type;
        if (null == tradeMode) {
            type = "";
        } else if (BusTypeEnum.KAYE.code().equals(tradeMode)) {
            type = BusTypeEnum.KAYE.state().toString();
        } else {
            type = BusTypeEnum.KJDS.state().toString();
        }
```


#####车辆预约，选择车辆
正确逻辑:选择基于该货代账户下的驾驶员
当前问题:耦合性判断该公司是否被拉黑

建议处理方式:
第一种方案:配置拦截路径，前置判断
第二种方案:自定义注解，前置判断，对应方法标注

######车辆预约，选择车辆
正确逻辑:选择基于该货代账户下的驾驶员
当前问题:耦合性判断该公司是否被拉黑

建议处理方式:
第一种方案:配置拦截路径，前置判断
第二种方案:自定义注解，前置判断，对应方法标注

######556 ~ 568

```
@PostMapping("/list/driverList")
    @ResponseBody
    public TableDataInfo driverList(CustRecordPerson custRecordPerson) {
        CustRecordCompany company = custRecordCompanyService.getCompanyIdAndNameByUserId(ShiroUtils.getUserId(), BlackEnum.BLACK_STATUS_REMOVE.getState());
        if (null != company && SysExamineEnum.RECORDED.getState().equals(company.getAuditState())) {
            custRecordPerson.setBlackMark(BlackEnum.BLACK_STATUS_REMOVE.getState());
            custRecordPerson.setCompanyId(company.getCompanyId());
            custRecordPerson.setAuditStatus(SysExamineEnum.RECORDED.getState());
            startPage();
            return getDataTable(custRecordPersonService.getCustRecordPersonList(custRecordPerson));
        }
        return getDataTable(new ArrayList<>());
    }
```


#####车辆预约，取消
问题:未验证非空
处理方式:加判空验证

######车辆预约，取消
问题:未验证非空
处理方式:加判空验证

######478 ~ 478

```
CarReservation number = carReservationService.getById(carReservation.getId());
```


#####车辆预约，对象转换
问题：相同类型参数很多，合理使用转换工具，必要设置使用明确式设置
处理方式：OrikaUtils.convert(S,T)

######车辆预约，对象转换
问题：相同类型参数很多，合理使用转换工具，必要设置使用明确式设置
处理方式：OrikaUtils.convert(S,T)

######39 ~ 73

```
public static CarReservation convertToCarReservation(CarReservationApplyDTO dto, Long userId) {
        CarReservation carReservation = new CarReservation();
        carReservation.setBackupsNumber(dto.getBackupsNumber());
        carReservation.setBsTimeline(dto.getBsTimeline());
        carReservation.setCarNumber(dto.getCarNumber());
        carReservation.setCustomsInspection(CustomsInspectionEnum.NO.state());
        carReservation.setCarType(dto.getCarType());
        carReservation.setCertificateNumber(dto.getCertificateNumber());
        carReservation.setReservationDate(LocalDate.parse(dto.getReservationDate()));
        carReservation.setCreateTime(new Date());
        carReservation.setCreateBy(userId);
        carReservation.setCarBusType(dto.getCarBusType());
        carReservation.setState(CarReservationState.RESERVED.getState());
        carReservation.setZoneCode(ZoneCodeEnum.JF_ZONE.zoneCode());
        carReservation.setVehteamFlag(dto.getVehteamFlag());
        carReservation.setChangeFlag(dto.getChangeFlag());
        carReservation.setIoType(IoTypeEnum.ONE_ZERO_ONE.code());
        carReservation.setBattleName(BattleNameEnum.JF.code());
        carReservation.setWareName(WareNameEnum.JF.code());
        Integer num = 0;
        Double roughWeight = 0D;
        for (CarReservationDetailDTO detail : dto.getBusinessList()) {
            num += Double.valueOf(detail.getPackNo()).intValue();
            roughWeight += Double.parseDouble(detail.getGrossWeight());
        }
        carReservation.setNum(num);
        carReservation.setRoughWeight(roughWeight);
        CarBusTypeEnum carBusTypeEnum = CarBusTypeEnum.getCarBusTypeEnum(dto.getCarBusType());
        carReservation.setCustomsTransit(carBusTypeEnum.getCustomsTransit());
        carReservation.setCarriageType(carBusTypeEnum.getCarriageType());
        carReservation.setCargoportAreaType(carBusTypeEnum.getAreaType());
        carReservation.setDispatchState(CallStatusEnum.WAIT_YOUR_TURN.state().longValue());
        carReservation.setPhone(dto.getPhone());
        return carReservation;
    }
```


#####车辆预约，取消预约
问题:并列条件
处理方法：合并if

######车辆预约，取消预约
问题:并列条件
处理方法：合并if

######481 ~ 486

```
if (CallStatusEnum.WAIT_YOUR_TURN.state().equals(number.getDispatchState().intValue()) || null == number.getDispatchState()) {
                if (CarReservationState.RESERVED.getState().equals(number.getState())) {
                    carReservation.setUserId(ShiroUtils.getUserId());
                    return toAjax(carReservationService.cancel(carReservation));
                }
            }
```


#####车辆预约，判断车辆占用 问题1:车牌是否可以like notIn的条件是否足够 问题2:针对修改检测是否自己的车牌，代码检索预约信息，再次嵌套遍历多余  处理： 问题1需要斟酌 问题2:lambda查询时如果预约编号不存在，则加上预约编号不等于当前预约编号即可过滤

######车辆预约，判断车辆占用 问题1:车牌是否可以like notIn的条件是否足够 问题2:针对修改检测是否自己的车牌，代码检索预约信息，再次嵌套遍历多余  处理： 问题1需要斟酌 问题2:lambda查询时如果预约编号不存在，则加上预约编号不等于当前预约编号即可过滤

######1585 ~ 1604

```
int noReserveCar = this.count(Wrappers.<CarReservation>lambdaQuery()
                    .like(CarReservation::getCarNumber, car)
                    .eq(CarReservation::getReservationDate, strDate)
                    .eq(CarReservation::getBsTimeline, bsTimeline)
                    .notIn(CarReservation::getState, CarReservationState.COMPLETED.getState(), CarReservationState.CANCEL.getState()));
            if (noReserveCar > 0) {
                List<String> carList = Arrays.asList(carReservationDTO.getCarNumber().split(","));
                CarReservation reservation = this.getCarReservationByReservationNumber(carReservationDTO.getReservationNumber());
                for (String s : carList) {
                    for (int i = 0; i < noReserveCar; i++) {
                        if (null != reservation && s.equals(car)) {
                            continue;
                        }
                        if (s.equals(car)) {
                            log.error("[{}],在当前时段已经预约过了!", s);
                            throw new BusinessException("车牌号 [" + s + "] 当前时段已预约");
                        }
                    }
                }
            }
```


#####车辆预约，运单航班校验
问题：此处进行了运单校验，1388持续进行运单，航班等信息验证
处理方法：该处验证下移，与底账&航班信息集中严重，避免多次查询

######车辆预约，运单航班校验
问题：此处进行了运单校验，1388持续进行运单，航班等信息验证
处理方法：该处验证下移，与底账&航班信息集中严重，避免多次查询

######1363 ~ 1369

```
for (CarReservationDetailDTO carReservationDetailDTO : carReservationDTO.getBusinessList()) {
            checkIEBusByCompany(carReservationDetailDTO, custRecordCompany);
            if (StringUtils.isEmpty(carReservationDetailDTO.getDocNo())) {
                log.error("运单号:[{}],单证编号为空,请补齐信息后再提交!", carReservationDetailDTO.getBillNo());
                throw new BusinessException("单证编号不能为空");
            }
        }
```


#####车辆预约，出口航班数据校验
问题1:航班通过date查询出来，if(data!=flightDate)多余
问题2:航班只要少于起飞时间判断包含已起飞判断，是否需要这么详细

处理方式:1.删除多余判断
2. 删除多于判断


######车辆预约，出口航班数据校验
问题1:航班通过date查询出来，if(data!=flightDate)多余
问题2:航班只要少于起飞时间判断包含已起飞判断，是否需要这么详细

处理方式:1.删除多余判断
2. 删除多于判断


######424 ~ 450

```
 FlightInfo flightInfo = flightInfoService.getByCarrierAndNoAndDate(waybillVo.getCarrier(), waybillVo.getFlightNo(), date, FlightStateEnum.RECOVERY.getValue());
                    if (null == flightInfo) {
                        log.error("运单号:[{}],未找到相关的航班信息", carReservationDetailDTO.getBillNo());
                        throw new BusinessException("运单 [" + carReservationDetailDTO.getBillNo() + "] 承运航班不存在");
                    }

                    //航班时段
                    String flightDate = flightInfo.getFlightDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                    if (!date.equals(flightDate)) {
                        log.error("运单号:[{}],未找到相关的航班信息", carReservationDetailDTO.getBillNo());
                        throw new BusinessException("运单 [" + carReservationDetailDTO.getBillNo() + "] 承运航班不存在");
                    } else {
                        //计算相差分钟数
                        long l = ((flightInfo.getStartPlanTime().getTime()) / (60000));
                        long n = System.currentTimeMillis() / (60000);
                        long r = (l - n);
                        if (r <= 0) {
                            log.error("运单号:[{}],航班日期:[{}],计划起飞时间:[{}],航班已起飞,不允许预约!", carReservationDetailDTO.getBillNo(), flightInfo.getFlightDate(), flightInfo.getStartPlanTime());
                            throw new BusinessException("运单 [" + carReservationDetailDTO.getBillNo() + "] 承运航班 [" + flightInfo.getFlightNo() + "] 已起飞");
                        }
                        int time = Integer.parseInt(sysConfigService.getConfigByKey(SysConfigConstants.TAKEOFF_BAN_RESERVE_TIME));
                        if (r <= time) {
                            //运单 [xxxx] 承运航班 [xxxx] 即将起飞，不能预约
                            log.error("运单号:[{}],航班日期:[{}],计划起飞时间:[{}],距离航班计飞时间应大于或等于[{}]分钟,实际为[{}]分钟,不能预约!!", carReservationDetailDTO.getBillNo(), flightInfo.getFlightDate(), flightInfo.getStartPlanTime(), time, r);
                            throw new BusinessException("运单 [" + carReservationDetailDTO.getBillNo() + "] 承运航班 [" + flightInfo.getFlightNo() + "] 即将起飞，不能预约");
                        }
                    }
```


#####车辆预约，选择运单
当前问题:耦合性判断该公司是否被拉黑

建议处理方式:
第一种方案:配置拦截路径，前置判断
第二种方案:自定义注解，前置判断，对应方法标注

######车辆预约，选择运单
当前问题:耦合性判断该公司是否被拉黑

建议处理方式:
第一种方案:配置拦截路径，前置判断
第二种方案:自定义注解，前置判断，对应方法标注

######602 ~ 612

```
@PostMapping("/list/unload/{carBusType}")
    @ResponseBody
    public TableDataInfo listUnload(@PathVariable("carBusType") String carBusType, String condition, String tradeMode, String billNo, String ieType) {
        CustRecordCompany company = custRecordCompanyService.getCompanyIdAndNameByUserId(ShiroUtils.getUserId(), BlackEnum.BLACK_STATUS_REMOVE.getState());
        if (null == company) {
            log.error("该账户存在异常！请联系货站客服申请解禁处理！");
            return getDataTable(new ArrayList<>());
        }
        startPage();
        return getDataTable(carReservationService.getCarReservationBusList(carBusType, condition, tradeMode, billNo, ieType, company));
    }
```


#####车辆预约
问题:这种时许太低
处理方式:与运单校验放在一块，提高时许

######车辆预约
问题:这种时许太低
处理方式:与运单校验放在一块，提高时许

######251 ~ 251

```
documentsProcess(carReservationDTO);
```


#####车辆预约，取消预约
问题:内部数据所有状态进行运算，本质上只是想确定是否都是待叫号
处理方式：检测到非叫号状态，直接打回

######车辆预约，取消预约
问题:内部数据所有状态进行运算，本质上只是想确定是否都是待叫号
处理方式：检测到非叫号状态，直接打回

######601 ~ 606

```
 String state = findDispatchState(queueInfoByReservationNo, null);
            if (CallStatusEnum.WAIT_YOUR_TURN.state().equals(Integer.parseInt(state))) {
                //减少当前时间段预约人数
                log.info("减少日期:[{}],时间段:{{}]预约人数", number.getReservationDate(), number.getBsTimeline());
                reducingProcess(number);
            }
```


#####车辆预约，重新预约
问题1：

######车辆预约，重新预约
问题1：

######448 ~ 459

```
 if ( CallStatusEnum.WAIT_YOUR_TURN.state().equals(number.getDispatchState().intValue())
                    || null == number.getDispatchState()
                    || CallStatusEnum.Cancel.state().equals(number.getDispatchState().intValue())) {
                if (null == number.getAuditState() || CarAuditStateEnum.FAIL_AUDIT.state().equals(number.getAuditState().toString()) ||
                        CarAuditStateEnum.WAIT_AUDIT.state().equals(number.getAuditState().toString()) || (CarAuditStateEnum.SUCCESS_AUDIT.state().equals(number.getAuditState().toString()))) {
                    //数据校验
                    carReservation.setUserId(ShiroUtils.getUserId());
                    carReservationService.dataValidation(carReservation);
                    carReservation.setChangeFlag(ChangeFlagEnum.UPDATE.operateCode());
                    return toAjax(carReservationService.reservationAddOrUpdate(carReservation, ShiroUtils.getUserId()));
                }
            }
```


#####车辆预约
问题：为什么要在预约|重新预约是移除数据

处理方式：在取消或被拒绝时移除数据

######车辆预约
问题：为什么要在预约|重新预约是移除数据

处理方式：在取消或被拒绝时移除数据

######184 ~ 184

```
removeData(carReservationDTO.getReservationNumber());
```


#####车辆预约
问题：为什么需要进行连续校验
处理方式:可以将运单+底账+航班进行一起统一验证，防止多处操作，可以使用乐观锁方式或其他原子性方式锁定运单


######车辆预约
问题：为什么需要进行连续校验
处理方式:可以将运单+底账+航班进行一起统一验证，防止多处操作，可以使用乐观锁方式或其他原子性方式锁定运单


######193 ~ 193

```
repeatValidation(carReservationDTO.getBusinessList());
```


#####车辆预约，选择车辆 正确逻辑:选择基于该货代账户下的车辆 当前问题:耦合性判断该公司是否被拉黑 建议处理方式: 第一种方案:配置拦截路径，前置判断 第二种方案:自定义注解，前置判断，对应方法标注

######车辆预约，选择车辆 正确逻辑:选择基于该货代账户下的车辆 当前问题:耦合性判断该公司是否被拉黑 建议处理方式: 第一种方案:配置拦截路径，前置判断 第二种方案:自定义注解，前置判断，对应方法标注

######539 ~ 548

```
@PostMapping("/list/getCarNoList")
    @ResponseBody
    public TableDataInfo getCarNoList(RecordCarVo recordCarVo) {
        CustRecordCompany company = custRecordCompanyService.getCompanyIdAndNameByUserId(ShiroUtils.getUserId(), BlackEnum.BLACK_STATUS_REMOVE.getState());
        if (null != company && SysExamineEnum.RECORDED.getState().equals(company.getAuditState())) {
            startPage();
            return getDataTable(custRecordCarService.getCarNoList(company.getCompanyId(), recordCarVo.getCompanyName(), recordCarVo.getLicensePlateNum()));
        }
        return getDataTable(new ArrayList<>());
    }
```


#####车辆预约，检测选择选择提货和卸货是否都有运单
问题:在大量验证之后

处理方式:非数据库和大量业务处理的校验提高校验时序，有效控制流程

######车辆预约，检测选择选择提货和卸货是否都有运单
问题:在大量验证之后

处理方式:非数据库和大量业务处理的校验提高校验时序，有效控制流程

######1393 ~ 1393

```
pickAndUnload(carReservationDTO)
```


#####车辆预约 选择日期
问题：for循环内部过度查询，且通过get(i)获取字典键值顺序并不能保证一对一

处理方式:
1.查询时间线提取到外层
2.时间线转Map,精确获取


######车辆预约 选择日期
问题：for循环内部过度查询，且通过get(i)获取字典键值顺序并不能保证一对一

处理方式:
1.查询时间线提取到外层
2.时间线转Map,精确获取


######102 ~ 115

```
 @Override
    public List<CustNumAppointmentsVo> getReservationNumList(String reservationDate) {
        List<CustNumAppointmentsVo> list = new ArrayList<>(24);
        List<CustNumAppointments> appointments = this.list(new QueryWrapper<CustNumAppointments>().select("id", "current_num").eq("appointment_date", reservationDate));
        for (int i = 0; i < appointments.size(); i++) {
            CustNumAppointmentsVo vo = new CustNumAppointmentsVo();
            vo.setCurrentNum(appointments.get(i).getCurrentNum().intValue());
            List<CustNumAppointmentsVo> dataList = sysDictDataService.selectSysDictDataList(TimelineConstants.BS_TIMELINE);
            vo.setDictLabel(dataList.get(i).getDictLabel());
            vo.setDictValue(dataList.get(i).getDictValue());
            list.add(vo);
        }
        return list;
    }
```



进程已结束,退出代码0
